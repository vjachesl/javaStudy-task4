package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Action factory for action classes new object creating.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 10, 2015.
 */

public class ActionFactory {

    // logger object
    private static final Logger LOG = Logger.getLogger(ActionFactory.class);

    //map with
    private static final Map<String, Action> actions;

    static {
        actions = new HashMap<String, Action>();
        actions.put("/admin_login", new AdminLogin());
        actions.put("/admin_cabinet", new AdminCabinet());
        actions.put("/admin_logout", new AdminLogout());
        actions.put("/routes", new Routes());
        actions.put("/route_detailes", new RouteDetailes());
        actions.put("/route_transport_units", new RouteTransportUnits());
        actions.put("/admin_new_route", new AdminNewRoute());
        actions.put("/admin_del_rout", new AdminDeleteRoute());
        actions.put("/admin_new_stop", new AdminNewStop());
        actions.put("/admin_del_stop", new AdminDeleteStop());
        actions.put("/admin_ass_stop", new AdminNewRouteStop());
        actions.put("/admin_rem_stop", new AdminRemoveRouteStop());
        actions.put("/admin_ass_unit", new AdminNewRouteUnit());
        actions.put("/admin_rem_unit", new AdminRemoveRouteUnit());
        actions.put("/admin_new_unit", new AdminNewUnit());
        actions.put("/admin_del_unit", new AdminDeleteUnit());

    }

    /**
     * Method returns result of action object working
     *
     * @param request the request object for parametrs getting ability
     * @return String - result of action
     */
    public static Action getAction(HttpServletRequest request) {
        LOG.debug("tryed to ask factory for action = " + request.getPathInfo());
        return actions.get(request.getPathInfo());
    }
}
