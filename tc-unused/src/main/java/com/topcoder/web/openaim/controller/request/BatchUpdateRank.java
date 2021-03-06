package com.topcoder.web.openaim.controller.request;

import com.topcoder.web.common.HibernateUtils;
import com.topcoder.web.common.validation.IntegerValidator;
import com.topcoder.web.common.validation.StringInput;
import com.topcoder.web.common.validation.ValidationResult;
import com.topcoder.web.openaim.Constants;
import com.topcoder.web.openaim.dao.OpenAIMDAOUtil;
import com.topcoder.web.openaim.dao.SubmissionDAO;
import com.topcoder.web.openaim.model.ContestStatus;
import com.topcoder.web.openaim.model.Submission;
import com.topcoder.web.openaim.model.SubmissionType;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author dok
 * @version $Revision: 68803 $ Date: 2005/01/01 00:00:00
 *          Create Date: Nov 20, 2006
 */
public class BatchUpdateRank extends BaseSubmissionDataProcessor {
    protected void dbProcessing() throws Exception {

        Long contestId = new Long(getRequest().getParameter(Constants.CONTEST_ID));

        Integer submissionTypeId = new Integer(getRequest().getParameter(Constants.SUBMISSION_TYPE_ID));

        SubmissionDAO dao = OpenAIMDAOUtil.getFactory().getSubmissionDAO();
        String paramName;
        String newRank;
        Integer newRankInt;
        Submission currSubmission = null;
        Date now = new Date();
        List userSubmissions = dao.getSubmissions(contestId, getUser().getId(), submissionTypeId);
        Integer maxRank = null;
        ArrayList<Integer> newRanks = new ArrayList<Integer>(userSubmissions.size());
        ArrayList changedSubmissions = new ArrayList(userSubmissions.size());
        for (Enumeration paramNames = getRequest().getParameterNames(); paramNames.hasMoreElements();) {
            paramName = (String) paramNames.nextElement();
            if (paramName.startsWith(Constants.SUBMISSION_ID)) {
                newRank = getRequest().getParameter(paramName);
                ValidationResult r = new IntegerValidator("Please enter a valid number.").validate(new StringInput(newRank));
                if (!r.isValid()) {
                    addError(paramName, r.getMessage());
                } else {
                    currSubmission = findSubmission(userSubmissions,
                            new Long(paramName.substring(Constants.SUBMISSION_ID.length())));
                    if (submissionTypeId.equals(SubmissionType.INITIAL_CONTEST_SUBMISSION_TYPE) && (now.before(currSubmission.getContest().getStartTime()) ||
                            now.after(currSubmission.getContest().getEndTime()) ||
                            !ContestStatus.ACTIVE.equals(currSubmission.getContest().getStatus().getId()))) {
                        addError(paramName, "Sorry, you can not make a change to a submission for a contest that is not active.");
                    } else {
                        if (maxRank == null) {
                            maxRank = dao.getMaxRank(currSubmission.getContest(), currSubmission.getSubmitter());
                        }
                        newRankInt = new Integer(newRank);
                        if (newRankInt < 1) {
                            newRankInt = 1;
                        } else if (newRankInt > maxRank) {
                            newRankInt = maxRank;
                        }
                        if (!newRankInt.equals(currSubmission.getRank())) {
                            newRanks.add(newRankInt);
                            changedSubmissions.add(currSubmission);
                        }
                    }
                }
            }
        }

//todo make it smarter, since the transaction isn't yet commited, it seems to be confused since we're updated the same rows
        //todo multiple times.  perhaps we have to do it all here in the processor instead or something.
        Submission s = (Submission) userSubmissions.get(0);
        if (!hasErrors()) {

            for (int i = 0; i < newRanks.size(); i++) {
                dao.changeRank((Integer) newRanks.get(i), (Submission) changedSubmissions.get(i));
            }

            closeConversation();

            beginCommunication();

            HibernateUtils.getSession().refresh(s);
            dao = OpenAIMDAOUtil.getFactory().getSubmissionDAO();
        }

        loadSubmissionData(s.getSubmitter(), s.getContest(), dao, maxRank, s.getType().getId());

        if (hasErrors()) {
            //override so that the user gets their data back to them
            for (Enumeration paramNames = getRequest().getParameterNames(); paramNames.hasMoreElements();) {
                paramName = (String) paramNames.nextElement();
                setDefault(paramName, getRequest().getParameter(paramName));
            }
        }

        setIsNextPageInContext(true);
        setNextPage("submitTableBody.jsp");


    }


    private Submission findSubmission(List submissions, Long id) {
        Submission curr = null;
        for (Object submission : submissions) {
            curr = (Submission) submission;
            if (curr.getId().equals(id)) {
                return curr;
            }
        }
        return null;
    }
}
