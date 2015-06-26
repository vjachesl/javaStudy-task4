package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.chichin.cityTransport.control.Util.InputValidation.isPositiveDecimalNumber;

/**
 * Class for deleting stop from system
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public class AdminDeleteRoute implements Action {

    // logger object
    private static final Logger LOG = Logger.getLogger(AdminDeleteRoute.class);

    /**
     * Method checks if user logged in,
     * checks if it receive correct parametr and Route existing
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
        //receiving routeId parametr and check if it valid number
        LOG.debug("was processed with the next param" + request.getParameter("route_id"));
        if (isPositiveDecimalNumber((String) request.getParameter("route_id")) == false) {
            request.getSession().setAttribute("message", "You was input invalid parametrs. Please try again!");
            return "admin_result";
        }
        // Checking valid route from DAO
        Route route = DataSourceDaoFactory.getDAOFactory().getRouteDao().getRoute(Integer.parseInt(request.getParameter("route_id")));
        if (route == null) {
            LOG.debug("dao said - this number is not exists");
            request.setAttribute("message", "You tried delete not existing route! Please reload page!");
            return "admin_result";
        }
        // deleting route and analysing results
        int routeId = Integer.parseInt(request.getParameter("route_id"));
        int result = DataSourceDaoFactory.getDAOFactory().getRouteDao().removeRoute(routeId);
        if (result == 0) {
            request.setAttribute("message", "some error. Route was n't delete");
            LOG.info("was not delete");
            return "admin_result";
        } else {
            request.setAttribute("message", "Route was deleted sussesfully!");
            LOG.info("was deleted route " + routeId + " by user" + request.getSession().getAttribute("admin").toString());
            request.getSession().setAttribute("routes", DataSourceDaoFactory.getDAOFactory().getRouteDao().getAllRoutes());
            return "admin_result";
        }
    }
}
