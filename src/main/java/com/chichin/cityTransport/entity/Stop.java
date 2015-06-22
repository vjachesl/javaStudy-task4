package com.chichin.cityTransport.entity;

/**
 * Created by viacheslav on 11.06.15.
 */
public class Stop {
    private final int STOP_ID;
    private String nameEn;
    private String nameRu;
    private GeoPoint geoPoint;



    public Stop (int stopId, String nameEn, String nameRu,double lat, double lng){
        this.STOP_ID = stopId;
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        geoPoint = new GeoPoint(lat,lng);
    }

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
