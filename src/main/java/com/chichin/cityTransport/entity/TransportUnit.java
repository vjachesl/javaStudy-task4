package com.chichin.cityTransport.entity;
import static com.chichin.cityTransport.entity.init.TransportSystemSettingConstants.*;

/**
 * Created by viacheslav on 11.06.15.
 */
public class TransportUnit {
    private final int UNIT_ID;
    private final String MODEL_NAME_EN;
    private final String MODEL_NAME_RU;
    private TransportTypes TRANSPORT_TYPE;
    private Route route;

    public TransportUnit(int UNIT_ID, String modelNameEn, String modelNameRu, TransportTypes TRANSPORT_TYPE, Route route) {
        this.MODEL_NAME_EN = modelNameEn;
        this.MODEL_NAME_RU = modelNameRu;
        this.UNIT_ID = UNIT_ID;
        this.TRANSPORT_TYPE = TRANSPORT_TYPE;
        this.route = route;
    }

    public int UNIT_ID() {
        return UNIT_ID;
    }

    public String MODEL_NAME_EN() {
        return MODEL_NAME_EN;
    }

    public String MODEL_NAME_RU() {
        return MODEL_NAME_EN;
    }

    public TransportTypes TRANSPORT_TYPE() {
        return TRANSPORT_TYPE;
    }

    public void setOnRoute(TransportUnit unit, Route route){
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }

    public void relizefromRoute(TransportUnit unit, Route route){
        this.route=null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportUnit that = (TransportUnit) o;

        if (UNIT_ID != that.UNIT_ID) return false;
        if (!(MODEL_NAME_EN.equals(that.MODEL_NAME_EN))) return false;
        return TRANSPORT_TYPE == that.TRANSPORT_TYPE;
    }

    @Override
    public int hashCode() {
        int result = UNIT_ID;
        result = 31 * result + MODEL_NAME_EN.hashCode();
        result = 31 * result + TRANSPORT_TYPE.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TransportUnit{" +
                ", TRANSPORT_TYPE=" + TRANSPORT_TYPE +
                "UNIT_ID=" + UNIT_ID +
                ", modelName='" + MODEL_NAME_EN + '\'' +
                '}';
    }
}
