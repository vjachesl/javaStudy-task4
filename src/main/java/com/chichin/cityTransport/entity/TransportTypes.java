package com.chichin.cityTransport.entity;

/**
 * Created by viacheslav on 11.06.15.
 */
public enum TransportTypes {
    TRAM("Трамвай","Tram"),
    BUS("Автобус","Bus"),
    TROLLEYBUS("Троллейбус","Trolleybus");

    String nameRus;
    String nameEn;
    TransportTypes(String val1, String val2) {
        this.nameRus = val1;
        this.nameEn = val2;
    }

    public String getNameEn(){
        return nameEn;
    }

    public String getNameRus(){
        return nameRus;
    }
}