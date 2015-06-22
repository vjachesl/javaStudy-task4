package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.Stop;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminDeleteStop implements Action {
    private static final Logger LOG = Logger.getLogger(AdminDeleteStop.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "admin_cabinet";
        }
        LOG.debug("was processed with the next param" + request.getParameter("stop_id"));
        Stop stop = DataSourceDaoFactory.getDAOFactory().getStopsDao().getStop(Integer.parseInt(request.getParameter("stop_id")));
        if (stop==null) {
            LOG.debug("dao said - this number is not exists");
            request.getSession().setAttribute("message", "You tried delete not existing stop! Please reload page!");
            return "admin_cabinet";
        }
        int stopId = Integer.parseInt(request.getParameter("stop_id"));
        int result = DataSourceDaoFactory.getDAOFactory().getStopsDao().removeStop(stopId);
        if (result==0) {
            request.getSession().setAttribute("message", "some error. Stop was n't delete");
            LOG.info("was not delete");
            return "admin_cabinet";
        }
        else {
            request.getSession().setAttribute("message", "Stop was deleted sussesfully!");
            LOG.info("was deleted stop " + stopId + " by user" + request.getSession().getAttribute("admin").toString());
            return "admin_cabinet";
        }
    }
}
