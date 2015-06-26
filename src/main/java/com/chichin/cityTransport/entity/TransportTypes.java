package com.chichin.cityTransport.entity;

/**
 * Class for Transport Type storing as 2 language Enum object.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public enum TransportTypes {
    TRAM("Трамвай", "Tram"),
    BUS("Автобус", "Bus"),
    TROLLEYBUS("Троллейбус", "Trolleybus");

    String nameRu;
    String nameEn;

    TransportTypes(String val1, String val2) {
        this.nameRu = val1;
        this.nameEn = val2;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }
}
