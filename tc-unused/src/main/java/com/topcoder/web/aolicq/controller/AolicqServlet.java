package com.topcoder.web.aolicq.controller;

import com.topcoder.shared.util.TCResourceBundle;
import com.topcoder.shared.util.logging.Logger;
import com.topcoder.web.common.BaseServlet;
import com.topcoder.web.common.NavigationException;
import com.topcoder.web.common.PermissionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.MissingResourceException;

/**
 * @author dok
 * @version $Revision: 73726 $ Date: 2005/01/01 00:00:00
 *          Create Date: Jun 22, 2006
 */
public class AolicqServlet extends BaseServlet {
    private static final Logger log = Logger.getLogger(AolicqServlet.class);

    static {
        throttleEnabled = false;
    }

/*
    protected WebAuthentication createAuthentication(TCRequest request,
                                                     TCResponse response) throws Exception {
        return new BasicAuthentication(new SessionPersistor(request.getSession()), request, response,
                BasicAuthentication.OPENAIM_SITE);
    }
*/

    protected String getProcessor(String key) {
        String ret = super.getProcessor(key);
        if (ret.equals(key)) {
            //yuck, gonna throw errors all over the place
            TCResourceBundle bundle = new TCResourceBundle("Aolicq");
            try {
                ret = bundle.getProperty(key);
            } catch (MissingResourceException ignore) {
                //just return what we got
            }
        }
        return ret;
    }


    protected void handleException(HttpServletRequest request, HttpServletResponse response, Throwable e)
            throws Exception {
        log.error("caught exception, forwarding to error page", e);
        if (e instanceof PermissionException) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            request.setAttribute(MESSAGE_KEY, "Sorry, you do not have permission to access the specified resource.");
        } else if (e instanceof NavigationException) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute(MESSAGE_KEY, e.getMessage());
            if (((NavigationException) e).hasUrl())
                request.setAttribute(URL_KEY, ((NavigationException) e).getUrl());
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute(MESSAGE_KEY, "An error has occurred when attempting to process your request.");
        }
        request.setAttribute("exception", e);
        fetchRegularPage(request, response, ERROR_PAGE, true);
    }

}
