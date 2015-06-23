package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.entity.Route;
import com.chichin.cityTransport.entity.Stop;
import com.chichin.cityTransport.entity.TransportUnit;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by viacheslav on 16.06.15.
 */
public class RouteDetailes implements Action {
    private static final Logger LOG = Logger.getLogger(RouteDetailes.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int routeId = Integer.parseInt(request.getParameterNames().nextElement()); //todo - make param detail analysys;
        LOG.info("Processing with route ID " +routeId);
        if (routeId == 0) return "routes";
        else {
            DaoFactory daoFactory = DataSourceDaoFactory.getDAOFactory();
            List<Stop> allStops = daoFactory.getStopsDao().getRouteStops(routeId);
            request.getSession().setAttribute("routeId", routeId);
            List<TransportUnit> transportUnits = daoFactory.getTransportUnitsDao().getRouteTransportUnits(routeId);
            Route route = daoFactory.getRouteDao().getRoute(routeId);
            if (route!=null) {
                route.setStops(allStops);
                if (transportUnits!=null) {
                    route.setTransportUnits(transportUnits);
                    request.getSession().setAttribute("transportUnits", transportUnits);
                    request.getSession().setAttribute("routeUnitsQuantity", transportUnits.size());
                }
                double routeLength = route.getRouteLength();
                double routeTime = route.getRouteTimeDerth();
                request.getSession().setAttribute("allStops", allStops);
                request.getSession().setAttribute("routeLength", routeLength);
                request.getSession().setAttribute("routeTime", routeTime);
            }
            return "route_detailes";
        }
    }
}
