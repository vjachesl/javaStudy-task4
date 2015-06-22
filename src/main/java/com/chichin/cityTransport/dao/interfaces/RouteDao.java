package com.chichin.cityTransport.dao.interfaces;

import com.chichin.cityTransport.entity.Route;

import java.util.List;

/**
 * Created by viacheslav on 11.06.15.
 */
public interface RouteDao {
    public List<Route> getAllRoutes();
    public Route getRoute(int routeId);
    public int addNewRoute(Route route);
    public int removeRoute(int routeId);
}

