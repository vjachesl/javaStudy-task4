package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.chichin.cityTransport.control.Util.InputValidation.isPositiveDecimalNumber;
import static com.chichin.cityTransport.control.Util.ParsingParametrs.parseParametres;

/**
 * Class for new route unit assigning to the route
 * new unit will be added into route units
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public class AdminNewRouteUnit implements Action {

    // logger object
    private static final Logger LOG = Logger.getLogger(AdminNewRouteUnit.class);

    /**
     * Method checks if user logged in,
     * then make input parametrs validation and
     * than  if unit not exists in the route, add new unit to the route.
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
        //receiving all inputed parameters and check it fo validity
        int unitId = Integer.parseInt(request.getParameterNames().nextElement());
        LOG.debug("was processed with the next param" + unitId);
        //receiving parameter and check it for validity
        if (unitId <= 0) {
            request.setAttribute("message", "You tried assign not existing unit or not existing route! Please reload page!");
            return "admin_result";

        }
        // asking DAO for assigning stop to route
        int route_id = Integer.parseInt((String) request.getSession().getAttribute("route_id"));
        int result = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().assignTransportUnitOnRoute(unitId, route_id);
        if (result != 1) {
            LOG.debug("dao said - unit wasn't assigned for route - some error");
            request.setAttribute("message", "Unit wasn't assigned for route - some error! Please reload page!");
            return "admin_result";
        } else {
            request.setAttribute("message", "unit  was assigned sussesfully!");
            LOG.info("was assigned unit" + request.getParameter("unit_id") + " for route" + request.getParameter("route_id") + "by user" + request.getSession().getAttribute("admin").toString());
            request.getSession().removeAttribute(("route_units"));
            request.getSession().removeAttribute(("zero_units"));
            request.getSession().removeAttribute(("unit_id"));
            request.getSession().removeAttribute(("route_id"));
            return "admin_result";
        }
    }
}
