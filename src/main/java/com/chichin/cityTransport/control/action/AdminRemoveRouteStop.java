package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class for route stop removing from the route
 * stop just removes from the route
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class AdminRemoveRouteStop implements Action {
    // logger object
    private static final Logger LOG = Logger.getLogger(AdminRemoveRouteStop.class);

    /**
     * Method checks if user logged in,
     * then make input parametrs validation and
     * than check if stop assigned the route in the system,
     * and than remove it from the route.
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress - admin_result with the result of operation showing to user
     */

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // checks if the user logged in
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        // getting stopId from the request
        int stopId = Integer.parseInt(request.getParameterNames().nextElement());
        LOG.debug("was processed with the next param" + stopId);

        // if parametr value is not valid - make report to the user and do nothing
        if (stopId <= 0) {
            request.setAttribute("message", "You tried delete not existing stop or not existing route! Please reload page!");
            return "admin_result";
        }

        // getting route ID from session
        int route_id = Integer.parseInt((String) request.getSession().getAttribute("route_id"));

        // trying to remove stop from route
        int result = DataSourceDaoFactory.getDAOFactory().getStopsDao().removeStopFromRoute(stopId, route_id);
        if (result != 1) {
            LOG.debug("dao said - stop wasn't remove from route - some error");
            request.setAttribute("message", "Stop wasn't remove from route - some error! Please reload page!");
            return "admin_result";
        } else {
            request.setAttribute("message", "Stop  was deleted sussesfully!");
            LOG.info("was deleted stop" + request.getParameter("stop_id") + " from route" + request.getParameter("route_id") + "by user" + request.getSession().getAttribute("admin").toString());
            request.getSession().removeAttribute(("route_stops"));
            request.getSession().removeAttribute(("stop_id"));
            request.getSession().removeAttribute(("route_id"));
            return "admin_result";
        }
    }
}
