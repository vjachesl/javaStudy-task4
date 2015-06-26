package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.TransportUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class for preparing data for route_transport_units.jsp
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public class RouteTransportUnits implements Action {

    /**
     * Method receive all info about route transport units from DAO,
     * then put all info into session and transfer user to the jsp
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress - admin_result with the result of operation showing to user
     */

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int routeId = (Integer) request.getSession().getAttribute("routeId");
        List<TransportUnit> transportUnits = (List<TransportUnit>) request.getSession().getAttribute("transportUnits");
        if (routeId == 0 || transportUnits == null) return "routes";
        return "route_transport_units";
    }
}


