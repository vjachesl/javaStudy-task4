package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.chichin.cityTransport.control.Util.InputValidation.isPositiveDecimalNumber;
import static com.chichin.cityTransport.control.Util.ParsingParametrs.parseParametres;

/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminNewRouteUnit implements Action {
    private static final Logger LOG = Logger.getLogger(AdminNewRouteUnit.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        int unitId = Integer.parseInt(request.getParameterNames().nextElement());
        LOG.debug("was processed with the next param" + unitId);
        if (unitId<=0) {
            request.setAttribute("message", "You tried assign not existing unit or not existing route! Please reload page!");
            return "admin_result";

        }
        int route_id = Integer.parseInt((String)request.getSession().getAttribute("route_id"));
        int result= DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().assignTransportUnitOnRoute(unitId, route_id);
        if (result!=1) {
            LOG.debug("dao said - unit wasn't assigned for route - some error");
            request.setAttribute("message", "Unit wasn't assigned for route - some error! Please reload page!");
            return "admin_result";
        }
        else {
            request.setAttribute("message", "unit  was assigned sussesfully!");
            LOG.info("wasassigned unit"  + request.getParameter("unit_id") + " for route" + request.getParameter("route_id") + "by user" + request.getSession().getAttribute("admin").toString());
            request.getSession().removeAttribute(("route_units"));
            request.getSession().removeAttribute(("zero_units"));
            request.getSession().removeAttribute(("unit_id"));
            request.getSession().removeAttribute(("route_id"));
            return "admin_result";
        }
    }
}
