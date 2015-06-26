package com.chichin.cityTransport.dao.interfaces;

import com.chichin.cityTransport.entity.Route;

import java.util.List;

/**
 * Interface for creating contract for routes DAO implementation,
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public interface RouteDao {
    List<Route> getAllRoutes();

    Route getRoute(int routeId);

    int addNewRoute(Route route);

    int removeRoute(int routeId);
}

