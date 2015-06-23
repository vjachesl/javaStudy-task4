package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.Stop;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.chichin.cityTransport.control.Util.InputValidation.isPositiveDecimalNumber;

/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminNewRouteStop implements Action {
    private static final Logger LOG = Logger.getLogger(AdminNewRouteStop.class);


    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }

        int stopId = Integer.parseInt(request.getParameterNames().nextElement());
        LOG.debug("was processed with the next param" + stopId);

        if (stopId <= 0) {
            request.setAttribute("message", "You tried add not existing stop or not existing route! Please reload page!");
            return "admin_result";
        }
        int route_id = Integer.parseInt((String) request.getSession().getAttribute("route_id"));
        System.out.println(route_id+"  "+stopId);
        List<Stop> routeStops = DataSourceDaoFactory.getDAOFactory().getStopsDao().getRouteStops(route_id);
        int result = DataSourceDaoFactory.getDAOFactory().getStopsDao().assignStopOnRoute(stopId, route_id,routeStops.size()+1 );
        if (result != 1) {
            LOG.debug("dao said - stop wasn't remove from route - some error");
            request.setAttribute("message", "Stop wasn't remove from route - some error! Please reload page!");
            return "admin_result";
        } else {
            request.setAttribute("message", "Stop  was added sussesfully!");
            LOG.info("was deleted stop" + request.getParameter("stop_id") + " from route" + request.getParameter("route_id") + "by user" + request.getSession().getAttribute("admin").toString());
            request.getSession().removeAttribute(("route_stops"));
            request.getSession().removeAttribute(("stop_id"));
            request.getSession().removeAttribute(("route_id"));
            return "admin_result";
        }
    }
}
