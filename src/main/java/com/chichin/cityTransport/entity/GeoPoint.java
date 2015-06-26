package com.chichin.cityTransport.entity;

/**
 * Class for Geo Point objects.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class GeoPoint {
    /**
     * Stores Latitude as Double
     */
    public double lat;
    /**
     * Stores Longtude as Double
     */
    public double lng;

    /**
     * Constructor
     *
     * @param lat - Double latitude
     * @param lng - Double longtude
     */
    public GeoPoint(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    /**
     * Return Latitude
     *
     * @return - Double latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * Return Longtude
     *
     * @return - Double Longtude
     */
    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return lat + ", " + lng;
    }
}
