package com.chichin.cityTransport.dao.interfaces;

import com.chichin.cityTransport.entity.Stop;

import java.util.List;

/**
 * Created by viacheslav on 13.06.15.
 */
public interface StopsDao {
    public List<Stop> getAllStops();
    public Stop getStop(int stopId);
    public List<Stop> getRouteStops(int routeId);
    public int addNewStop(Stop stop);
    public int assignStopOnRoute(int stopId, int routeId, int orderByRoute);
    public int removeStopFromRoute(int stopId, int routeId);
    public int removeStop(int stopId);
}
