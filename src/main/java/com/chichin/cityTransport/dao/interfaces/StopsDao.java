package com.chichin.cityTransport.dao.interfaces;

import com.chichin.cityTransport.entity.Stop;

import java.util.List;

/**
 * Interface for creating contract for DAO stops implementation,
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public interface StopsDao {
    List<Stop> getAllStops();

    Stop getStop(int stopId);

    List<Stop> getRouteStops(int routeId);

    int addNewStop(Stop stop);

    int assignStopOnRoute(int stopId, int routeId, int orderByRoute);

    int removeStopFromRoute(int stopId, int routeId);

    int removeStop(int stopId);
}
