package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.entity.Route;
import com.chichin.cityTransport.entity.Stop;
import com.chichin.cityTransport.entity.TransportUnit;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class for preparing data for route_detailes.jsp
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class RouteDetailes implements Action {

    //logger object
    private static final Logger LOG = Logger.getLogger(RouteDetailes.class);

    /**
     * Method receive route ID from parameter and ask for additional info from DAO,
     * then put all info into session and transfer user to the jsp
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress - admin_result with the result of operation showing to user
     */

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //getting route ID
        int routeId = Integer.parseInt(request.getParameterNames().nextElement()); //todo - make param detail analysys;
        LOG.info("Processing with route ID " + routeId);

        if (routeId == 0) return "routes";
        else {
            DaoFactory daoFactory = DataSourceDaoFactory.getDAOFactory();
            List<Stop> allStops = daoFactory.getStopsDao().getRouteStops(routeId);
            request.getSession().setAttribute("routeId", routeId);
            List<TransportUnit> transportUnits = daoFactory.getTransportUnitsDao().getRouteTransportUnits(routeId);
            Route route = daoFactory.getRouteDao().getRoute(routeId);
            if (route != null) {
                route.setStops(allStops);
                if (transportUnits != null) {
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
