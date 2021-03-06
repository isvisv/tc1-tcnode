/*
 * Copyright (c) 2001-2014 TopCoder Inc.  All Rights Reserved.
 */
package com.topcoder.web.tc.controller.request.dr;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.topcoder.shared.dataAccess.Request;
import com.topcoder.shared.dataAccess.resultSet.ResultSetContainer;
import com.topcoder.shared.dataAccess.resultSet.ResultSetContainer.ResultSetRow;
import com.topcoder.shared.util.DBMS;
import com.topcoder.web.common.BaseProcessor;
import com.topcoder.web.common.CachedDataAccess;
import com.topcoder.web.common.cache.MaxAge;
import com.topcoder.web.tc.Constants;

/**
 * Copyright (c) 2001-2008 TopCoder, Inc. All rights reserved.
 * Only for use in connection with a TopCoder competition.
 *
 * @author pulky
 * @version $Id: Home.java 84876 2014-05-13 10:35:58Z VolodymyrK $
 * Create Date: Jul 4, 2008
 */
public class Home extends BaseProcessor {

   
    private static String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};

    /* (non-Javadoc)
     * @see com.topcoder.web.common.BaseProcessor#businessProcessing()
     */
    @Override
    protected void businessProcessing() throws Exception {

        Calendar now = new GregorianCalendar();
        int month = now.get(Calendar.MONTH)+1;
        int year = now.get(Calendar.YEAR);

        ResultSetContainer rsc = getPointsData(DBMS.TCS_OLTP_DATASOURCE_NAME, "dr_pool_detail", month, year);

        int designTrackId=0, developmentTrackId=0, studioTrackId=0;
        PoolPrize designPrize = new PoolPrize();
        PoolPrize developmentPrize = new PoolPrize();  
		PoolPrize studioPrize = new PoolPrize();        		
        for (ResultSetRow rsr : rsc) {
            if (rsr.getIntItem("track_type_id") == 1) {
                designTrackId = rsr.getIntItem("track_id");
                designPrize.addTotal(rsr.getDoubleItem("total_dr_points"));
                designPrize.addToday(rsr.getDoubleItem("total_dr_points_today"));
            } else if (rsr.getIntItem("track_type_id") == 2) {
                developmentTrackId = rsr.getIntItem("track_id");
                developmentPrize.addTotal(rsr.getDoubleItem("total_dr_points"));
                developmentPrize.addToday(rsr.getDoubleItem("total_dr_points_today"));
            } else if (rsr.getIntItem("track_type_id") == 3) {
                studioTrackId = rsr.getIntItem("track_id");
                studioPrize.addTotal(rsr.getDoubleItem("total_dr_points"));
                studioPrize.addToday(rsr.getDoubleItem("total_dr_points_today"));
            }
        }

        getRequest().setAttribute("designTrackId", designTrackId);
        getRequest().setAttribute("developmentTrackId", developmentTrackId);
        getRequest().setAttribute("studioTrackId", studioTrackId);

        getRequest().setAttribute("designPrize", designPrize);
        getRequest().setAttribute("developmentPrize", developmentPrize);
        getRequest().setAttribute("studioPrize", studioPrize);

        getRequest().setAttribute("monthName", monthName[month-1]);
        getRequest().setAttribute("pastNDays", Constants.DR_POINTS_LAST_N_DAYS);
        
        setNextPage("/digital_run/home.jsp");
        setIsNextPageInContext(true);        
    }

    /**
     * @return
     * @throws Exception
     */
    private ResultSetContainer getPointsData(String datasource, String query, int month, int year) throws Exception {
        CachedDataAccess cda = new CachedDataAccess(MaxAge.HALF_HOUR, datasource);
        Request dataRequest = new Request();
        dataRequest.setContentHandle(query);
        dataRequest.setProperty("month", String.valueOf(month));
        dataRequest.setProperty("year", String.valueOf(year));
        dataRequest.setProperty("days", Constants.DR_POINTS_LAST_N_DAYS);
        Map<String, ResultSetContainer> map = cda.getData(dataRequest);
        ResultSetContainer rsc = map.get(query);
        return rsc;
    }

    public class PoolPrize {
        double total = 0;
        double today = 0;

        public double getTotal() {
            return total;
        }
        public void addToday(double doubleItem) {
            today += doubleItem;
        }
        public void addTotal(double doubleItem) {
            total += doubleItem;
        }
        public void setTotal(double total) {
            this.total = total;
        }
        public double getToday() {
            return today;
        }
        public void setToday(double today) {
            this.today = today;
        }
    }
}
