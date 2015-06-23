package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import static com.chichin.cityTransport.control.Util.InputValidation.isPositiveDecimalNumber;


/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminRemoveRouteUnit implements Action {
    private static final Logger LOG = Logger.getLogger(AdminRemoveRouteUnit.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        int unitId = Integer.parseInt(request.getParameterNames().nextElement());
                LOG.debug("was processed with the next param" + unitId);
        if (unitId<=0) {
            request.setAttribute("message", "You tried delete not existing unit or not existing route! Please reload page!");
            return "admin_result";
        }
        int result= DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().removeTransportUnitFromRoute(unitId);
        if (result!=1) {
            LOG.debug("dao said - unit wasn't remove from route - some error");
            request.setAttribute("message", "Unit wasn't remove from route - some error! Please reload page!");
            return "admin_result";
        }
        else {
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
