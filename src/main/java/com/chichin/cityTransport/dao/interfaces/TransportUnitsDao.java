package com.chichin.cityTransport.dao.interfaces;


import com.chichin.cityTransport.entity.TransportUnit;
import java.util.List;

/**
 * Created by viacheslav on 11.06.15.
 */
public interface TransportUnitsDao {
    public TransportUnit getTransportUnit(int unitId);
    public List<TransportUnit> getAllTransportUnits();
    public List<TransportUnit> getRouteTransportUnits(int routeId);
    public int addNewTransportUnit(TransportUnit unit);
    public int assignTransportUnitOnRoute(int unitId, int routeId);
    public int removeTransportUnitFromRoute(int unitId);
}


