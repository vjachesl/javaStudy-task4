package com.chichin.cityTransport.entity;

/**
 * Created by viacheslav on 11.06.15.
 */
public class GeoPoint {
    public double lat;
    public double lng;

    public GeoPoint(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLat(){
        return lat;
    }

    public double getLng(){
        return lng;
    }

    @Override
    public String toString() {
        return lat + ", " + lng;
    }
}
