package com.chichin.cityTransport.entity;

/**
 * Class for Stops objects.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public class Stop {

    /**
     * Stores stop ID as final int
     */
    private final int STOP_ID;

    /**
     * Stores stop English Name as final String
     */
    private String nameEn;

    /**
     * Stores stop Russian Name as final String
     */
    private String nameRu;

    /**
     * Stores stop GeoPoint as GeoPoint object
     */
    private GeoPoint geoPoint;


    /**
     * Constructor
     *
     * @param stopId - route ID
     * @param nameEn - route English Name
     * @param nameRu - route Russian Name
     * @param lat    - Double latitude
     * @param lng    - Double longtude
     */
    public Stop(int stopId, String nameEn, String nameRu, double lat, double lng) {
        this.STOP_ID = stopId;
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        geoPoint = new GeoPoint(lat, lng);
    }

    // Getters for parameters
    public int STOP_ID() {
        return STOP_ID;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

}
