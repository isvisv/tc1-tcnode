package com.topcoder.web.reg.controller.request;

import com.topcoder.shared.util.logging.Logger;
import com.topcoder.web.common.BaseProcessor;
import com.topcoder.web.common.BaseServlet;

/**
 * @author dok
 * @version $Revision: 59673 $ Date: 2005/01/01 00:00:00
 *          Create Date: Apr 27, 2006
 */
public class Logout extends BaseProcessor {

    protected static final Logger log = Logger.getLogger(Logout.class);

    protected void businessProcessing() throws Exception {
        // Logs the user out
        getAuthentication().logout();

        // Invalidates the session
        getRequest().getSession().invalidate();

        // Go to active contests page
        String nextpage = (String)getRequest().getAttribute(BaseServlet.NEXT_PAGE_KEY);
        if (nextpage == null) nextpage = getRequest().getParameter(BaseServlet.NEXT_PAGE_KEY);
        if (nextpage == null) nextpage = getRequest().getHeader("Referer");
        if (nextpage == null) nextpage = getSessionInfo().getAbsoluteServletPath();
        setNextPage(nextpage);
        setIsNextPageInContext(false);
    }
}
