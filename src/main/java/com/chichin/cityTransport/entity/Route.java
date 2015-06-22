package com.chichin.cityTransport.entity;

import java.util.*;

import static com.chichin.cityTransport.entity.init.TransportSystemSettingConstants.*;
import static java.lang.Math.*;

/**
 * Created by viacheslav on 11.06.15.
 */
public class Route {
    private final int ROUTE_ID;
    private final String ROUTE_NAME_EN;
    private final String ROUTE_NAME_RU;

    private final TransportTypes ROUTE_TRANSPORT_TYPE;
    private List<TransportUnit> transportUnits;
    private List<Stop> stops;

    public Route( int ROUTE_ID, String routeNameEn, String routeNameRu, TransportTypes transportType) {
        this.ROUTE_ID = ROUTE_ID;
        this.ROUTE_NAME_EN = routeNameEn;
        this.ROUTE_NAME_RU = routeNameRu;
        this.ROUTE_TRANSPORT_TYPE = transportType;
        stops = new ArrayList<Stop>();
        transportUnits = new ArrayList<TransportUnit>();
    }
    public int ROUTE_ID() {
        return ROUTE_ID;
    }

    public String ROUTE_NAME_EN() {
        return ROUTE_NAME_EN;
    }
    public String ROUTE_NAME_RU() {
        return ROUTE_NAME_RU;
    }

    public TransportTypes ROUTE_TRANSPORT_TYPE() {
        return ROUTE_TRANSPORT_TYPE;
    }

    public List<TransportUnit> getTransportUnits() {
        return transportUnits;
    }
    public List<Stop> getStops() {
        return stops;
    }
    public void setTransportUnits(List<TransportUnit> transportUnits) {
        this.transportUnits = transportUnits;
    }
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public void addTransportUnit(TransportUnit unit){
        transportUnits.add(unit);
    }
    public boolean removeTransportUnit(TransportUnit unit){
       return transportUnits.remove(unit);
    }
    public void addStop(Stop stop){
        stops.add(stop);
    }
    public void removeStop(Stop stop){
        stops.remove(stop);
    }

    public double getRouteLength (){
        if (stops.size()<2) return 0;
        else {
            ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
            Iterator<Stop> iter = stops.iterator();
            while(iter.hasNext())
                points.add(iter.next().getGeoPoint());
            double routeLength = 0.;
            for(int i=1; i<points.size(); i++)
               routeLength += getDistanceFromOneStopToEnother(points.get(i-1),points.get(i));
           return routeLength;
        }
    }

    public int getRouteTimeDerth(){
       double length = getRouteLength();
        System.out.println("route length"+length);

       return 5;// (int) (length/CITY_AVERAGE_SPEED)*MINUTES_IN_HOUR;
    }

    // Рассчитываем расстояние между точками
    private double getDistanceFromOneStopToEnother(GeoPoint point1, GeoPoint point2) {
        final double dlng = deg2rad(point1.getLng() - point2.getLng());
        final double dlat = deg2rad(point1.getLat() - point2.getLat());
        final double a = sin(dlat / 2) * sin(dlat / 2) + cos(deg2rad(point2.getLat()))
                * cos(deg2rad(point1.getLat())) * sin(dlng / 2) * sin(dlng / 2);
        return 2 * EARTH_RADIUS* atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    /**
     * Преобразует значение из градусов в радианы
     *
     * @param degree
     * @return
     */
    private static double deg2rad(final double degree) {
        return degree * (Math.PI / 180);
    }

    @Override
    public String toString() {
        return "Route{" +
                "ROUTE_ID=" + ROUTE_ID +
                ", ROUTE_NAME_EN='" + ROUTE_NAME_EN + '\'' +
                ", ROUTE_TRANSPORT_TYPE=" + ROUTE_TRANSPORT_TYPE +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (ROUTE_ID != route.ROUTE_ID) return false;
        return ROUTE_NAME_EN.equals(route.ROUTE_NAME_EN);

    }

    @Override
    public int hashCode() {
        int result = ROUTE_ID;
        result = 31 * result + ROUTE_NAME_EN.hashCode();
        return result;
    }
}

