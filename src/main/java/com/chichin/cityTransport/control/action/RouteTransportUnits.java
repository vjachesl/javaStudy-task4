package com.chichin.cityTransport.control.action;
import com.chichin.cityTransport.entity.TransportUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by viacheslav on 16.06.15.
 */
public class RouteTransportUnits implements Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int routeId = (Integer) request.getSession().getAttribute("routeId");
        List<TransportUnit> transportUnits = (List<TransportUnit>) request.getSession().getAttribute("transportUnits");
        if (routeId == 0 || transportUnits == null) return "routes";
        return "route_transport_units";
    }
}


