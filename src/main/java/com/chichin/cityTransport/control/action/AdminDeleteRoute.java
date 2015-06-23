package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.Route;
import com.chichin.cityTransport.entity.TransportTypes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminDeleteRoute implements Action {
    private static final Logger LOG = Logger.getLogger(AdminDeleteRoute.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        LOG.debug("was processed with the next param" + request.getParameter("route_id"));
        Route route = DataSourceDaoFactory.getDAOFactory().getRouteDao().getRoute(Integer.parseInt(request.getParameter("route_id")));
        if (route==null) {
            LOG.debug("dao said - this number is not exists");
            request.setAttribute("message", "You tried delete not existing route! Please reload page!");
            return "admin_result";
        }
        int routeId = Integer.parseInt(request.getParameter("route_id"));
        int result = DataSourceDaoFactory.getDAOFactory().getRouteDao().removeRoute(routeId);
        if (result==0) {
            request.setAttribute("message", "some error. Route was n't delete");
            LOG.info("was not delete");
            return "admin_result";
        }
        else {
            request.setAttribute("message", "Route was deleted sussesfully!");
            LOG.info("was deleted route " + routeId + " by user" + request.getSession().getAttribute("admin").toString());
            request.getSession().setAttribute("routes", DataSourceDaoFactory.getDAOFactory().getRouteDao().getAllRoutes());
            return "admin_result";
        }
    }
}
