/*
 * TCS Screening Tool 1.1
 *
 * UpdatePreference.java
 *
 * 1.0 07/14/2004
 */
package com.topcoder.web.corp.controller.request.screening;

import com.topcoder.shared.dataAccess.DataAccessConstants;
import com.topcoder.shared.dataAccess.Request;
import com.topcoder.shared.dataAccess.resultSet.ResultSetContainer;
import com.topcoder.shared.util.ApplicationServer;
import com.topcoder.web.common.TCRequest;
import com.topcoder.web.common.TCWebException;
import com.topcoder.web.corp.common.Constants;
import com.topcoder.web.corp.common.PermissionDeniedException;
import com.topcoder.web.corp.common.ScreeningException;
import com.topcoder.web.corp.common.Util;
import com.topcoder.web.ejb.preferencelevel.PreferenceLevel;

import javax.naming.NamingException;
import javax.transaction.Status;
import javax.transaction.TransactionManager;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * <p>A request processor responsible to handle the requests to create/update the preference level relevant to specified
 * company for specified candidate.</p>
 * <p/>
 * <p>Prior to fulfilling the request this processor checks if the user trying to update the candidate's preference
 * level is granted a permission to access the candidate's info. If this is the case then the <code>Preference Level EJB
 * </code> is used either to create the preference level if it does not exist or update the existing preference level
 * with new value. Otherwise a <code>PermissionDeniedException</code> is thrown.</p>
 *
 * @author isv
 * @version 1.0 07/14/2004
 * @since Screening Tool 1.1
 */
public class UpdatePreference extends BaseScreeningProcessor {

    /**
     * A <code>String</code> name of the attribute of an incoming request to be set to <code>"true"</code> to indicate
     * if an operation was successful; <code>"false"</code> if not.
     */
    public final static String SUCCESS_ATTRIBUTE_NAME = "successful";

    /**
     * A public constructor. Does nothing and is provided only not to rely on a default constructor generated by the
     * compiler.
     */
    public UpdatePreference() {
        super();
    }

    /**
     * The main method of the processor performing the update of preference level relevant to specified company for
     * specified candidate. If a specified user is granted a permission to access the info for specified candidate then
     * the preference levele relevant to specified company is either created (if does not exist) or updated with new
     * value.
     *
     * @throws PermissionDeniedException if a user trying to update the preference level for specified candidate is not
     *                                   granted the appropriate permission.
     * @throws TCWebException            if any other error preventing the successful fulfillment of request occurs. This exception
     *                                   will wrap the original exception (for example, <code>NamingException</code>, <code>RemoteException
     *                                   </code>).
     */
    protected void screeningProcessing() throws TCWebException {

        TCRequest request = getRequest();

        // Set the "successful" flag to false so if any exception occurs then the caller should "know" that the
        // operation was unsuccessful
        request.setAttribute(SUCCESS_ATTRIBUTE_NAME, "false");

        long uid = getUser().getId();
        long cuid = Long.parseLong(request.getParameter(Constants.CANDIDATE_ID));

        // Check if the user is granted the permission to access the candidate's info
        checkAccess(uid, cuid);

        try {
            // Get the reference to Preference Level EJB
            PreferenceLevel bean = (PreferenceLevel) createEJB(getInitialContext(), PreferenceLevel.class);

            // Locate all necessary parameters to update the preference level
            long companyId = getCompanyId(uid);
            int level = Integer.parseInt(request.getParameter(Constants.PREFERENCE_LEVEL));

            // Start and run the transaction
            TransactionManager tm = (TransactionManager) getInitialContext().lookup(ApplicationServer.TRANS_MANAGER);
            tm.begin();

            try {
                // If the preference level does not exist then create a new one; otherwise update the existing
                // preference level with new value
                if (!bean.exists(Constants.DATA_SOURCE, companyId, cuid)) {
                    bean.createUserPreference(Constants.DATA_SOURCE, companyId, cuid, level, uid);
                } else {
                    bean.update(Constants.DATA_SOURCE, companyId, cuid, level, uid);
                }
            } catch (Exception e) {
                // Rollback the transaction if something went wrong
                if (tm != null && (tm.getStatus() == Status.STATUS_ACTIVE || tm.getStatus() == Status.STATUS_MARKED_ROLLBACK)) {
                    tm.rollback();
                }
                throw e;
            }

            // Commit the transaction if everything went smoothly
            tm.commit();

        } catch (NamingException e) {
            throw new TCWebException(e);
        } catch (RemoteException e) {
            throw new TCWebException(e);
        } catch (Exception e) {
            throw new TCWebException(e);
        }

        // Everything went smoothly - the "success" attribute should be set to true to indicate that the operation was
        // successful
        request.setAttribute(SUCCESS_ATTRIBUTE_NAME, "true");
    }

    /**
     * A helper method checking whether the specified user is granted a permission to access the specified candidate's
     * info or not. The method executes a <code>candidateInfo</code> query and checks if any data is returned in
     * response to the query. If no data is returned then the user is considered not to be granted a permission to
     * access the candidate's info and the <code>PermissionDeniedException</code> will be thrown to prevent the
     * subsequent program flow.
     *
     * @param uid  a <code>long</code> containing the ID of a user trying to access the candidate's info.
     * @param cuid a <code>long</code> containing the ID of a candidate the user tries to access.
     * @throws PermissionDeniedException if specified user is not granted a permission to access the candidate's info.
     * @throws TCWebException            if any other error occured while executing the query. This exception will wrap the
     *                                   original exception.
     */
    private void checkAccess(long uid, long cuid) throws PermissionDeniedException, TCWebException {

        // Construct the request
        Request dr = new Request();
        dr.setContentHandle(Constants.CANDIDATE_INFO);
        dr.setProperty("uid", String.valueOf(uid));
        dr.setProperty("cid", String.valueOf(cuid));

        Map map = null;

        try {
            map = Util.getDataAccess().getData(dr);
        } catch (Exception e) {
            throw new TCWebException(e);
        }

        if (map != null) {
            ResultSetContainer result = (ResultSetContainer) map.get(Constants.CANDIDATE_INFO);
            if (result.isEmpty()) {
                throw new PermissionDeniedException(getUser(),
                        "User not authorized to view information about candidate: "
                                + (dr.getProperty("cid") == null ? "?" : dr.getProperty("cid")));
            }
        } else {
            throw new TCWebException("The decision could not be made.");
        }
    }

    /**
     * A helper method obtaining the ID for a company the user updating the preference level for a candidate is from.
     * The method executes the <code>CONTACT_INFO_QUERY</code> using the <code>Query Tool</code> providing the user ID
     * obtained from incoming request and gets the company ID from returned result set.
     *
     * @param uid a <code>long</code> containing the ID of a user to get the company ID for.
     * @return a <code>long</code> containing the ID for a company the user is associated with.
     * @throws Exception if an unexpected error prevents the successful fulfillment of request.
     */
    private long getCompanyId(long uid) throws Exception {

        // Construct the request
        Request dataRequest = new Request();
        dataRequest.setProperty(DataAccessConstants.COMMAND, Constants.CONTACT_INFO_QUERY_KEY);
        dataRequest.setProperty("uid", String.valueOf(uid));

        // Execute the query
        Map map = Util.getDataAccess().getData(dataRequest);

        // Get the resulting data
        ResultSetContainer rsc = (ResultSetContainer) map.get(Constants.CONTACT_INFO_QUERY_KEY);
        if (rsc.size() != 1) {
            throw new ScreeningException("Contact result set size wrong(" + rsc.size() + ")");
        }

        ResultSetContainer.ResultSetRow row = (ResultSetContainer.ResultSetRow) rsc.get(0);
        return Long.parseLong(row.getItem("company_id").toString());
    }
}
