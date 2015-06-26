package com.chichin.cityTransport.dao.interfaces;


import com.chichin.cityTransport.entity.TransportUnit;

import java.util.List;

/**
 * Interface for creating contract for transport units DAO implementation,
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public interface TransportUnitsDao {
    TransportUnit getTransportUnit(int unitId);

    List<TransportUnit> getAllTransportUnits();

    List<TransportUnit> getRouteTransportUnits(int routeId);

    int addNewTransportUnit(TransportUnit unit);

    int assignTransportUnitOnRoute(int unitId, int routeId);

    int removeTransportUnitFromRoute(int unitId);

    int removeTransportUnit(int unitId);
}


