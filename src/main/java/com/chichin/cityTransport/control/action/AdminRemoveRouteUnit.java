package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class for remove unit, assigning to the route
 * unit will be removed from route
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class AdminRemoveRouteUnit implements Action {

    // logger object
    private static final Logger LOG = Logger.getLogger(AdminRemoveRouteUnit.class);

    /**
     * Method checks if user logged in,
     * then make input parametrs validation and
     * than if unit exists in the route, and remove unit from the route.
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress - admin_result with the result of operation showing to user
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        //receiving all inputed parameters and check it fo validity
        int unitId = Integer.parseInt(request.getParameterNames().nextElement());
        LOG.debug("was processed with the next param" + unitId);
        if (unitId <= 0) {
            request.setAttribute("message", "You tried delete not existing unit or not existing route! Please reload page!");
            return "admin_result";
        }
        // asking DAO for removing unit from route
        int result = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().removeTransportUnitFromRoute(unitId);
        if (result != 1) {
            LOG.debug("dao said - unit wasn't remove from route - some error");
            request.setAttribute("message", "Unit wasn't remove from route - some error! Please reload page!");
            return "admin_result";
        } else {
            request.setAttribute("message", "unit  was deleted sussesfully!");
            LOG.info("was deleted unit" + request.getParameter("unit_id") + " from route" + request.getParameter("route_id") + "by user" + request.getSession().getAttribute("admin").toString());
            request.getSession().removeAttribute(("route_units"));
            request.getSession().removeAttribute(("zero_units"));
            request.getSession().removeAttribute(("unit_id"));
            request.getSession().removeAttribute(("route_id"));
            return "admin_result";
        }
    }
}
