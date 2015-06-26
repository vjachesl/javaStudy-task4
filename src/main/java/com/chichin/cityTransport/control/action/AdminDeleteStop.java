package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.Stop;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chichin.cityTransport.control.Util.InputValidation.*;

/**
 * Class for deleting stop from system
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 16, 2015.
 */
public class AdminDeleteStop implements Action {
    // logger object
    private static final Logger LOG = Logger.getLogger(AdminDeleteStop.class);

    /**
     * Method checks if user logged in,
     * checks if it receive correct parametr and Stop existing
     * and delete it
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress error or result with result describing
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // checks if the user logged in
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        //receiving stopId parametr and check if it valid number
        LOG.debug("was processed with the next param" + request.getParameter("stop_id"));
        if (isPositiveDecimalNumber((String) request.getParameter("stop_id")) == false) {
            request.getSession().setAttribute("message", "You was input invalid parametrs. Please try again!");
            return "admin_result";
        }
        // Checking valid stop from DAO
        Stop stop = DataSourceDaoFactory.getDAOFactory().getStopsDao().getStop(Integer.parseInt(request.getParameter("stop_id")));
        if (stop == null) {
            LOG.debug("dao said - this number is not exists");
            request.setAttribute("message", "You tried delete not existing stop! Please reload page!");
            return "admin_result";
        }
        // deleting stop and analysing results
        int stopId = Integer.parseInt(request.getParameter("stop_id"));
        int result = DataSourceDaoFactory.getDAOFactory().getStopsDao().removeStop(stopId);
        if (result == 0) {
            request.setAttribute("message", "some error. Stop was n't delete");
            LOG.info("was not delete");
            return "admin_result";
        } else {
            request.setAttribute("message", "Stop was deleted sussesfully!");
            LOG.info("was deleted stop " + stopId + " by user" + request.getSession().getAttribute("admin").toString());
            return "admin_result";
        }
    }
}
