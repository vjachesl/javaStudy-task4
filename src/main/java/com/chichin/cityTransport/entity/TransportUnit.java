package com.chichin.cityTransport.entity;

/**
 * Class for Transport units objects.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class TransportUnit {
    /**
     * Stores unit ID as final int
     */
    private final int UNIT_ID;

    /**
     * Stores unit English Name as final String
     */
    private final String MODEL_NAME_EN;

    /**
     * Stores unit Russian Name as final String
     */
    private final String MODEL_NAME_RU;

    /**
     * Stores unit Transport Types as final Enum constant
     */
    private TransportTypes TRANSPORT_TYPE;

    /**
     * Stores route ID as final int
     */
    private int routeId;


    /**
     * Constructor
     *
     * @param UNIT_ID     - unit ID
     * @param modelNameEn - model English Name
     * @param modelNameRu - modelRussian Name
     * @param routeId     - route Transport Types
     */
    public TransportUnit(int UNIT_ID, String modelNameEn, String modelNameRu, TransportTypes TRANSPORT_TYPE, int routeId) {
        this.MODEL_NAME_EN = modelNameEn;
        this.MODEL_NAME_RU = modelNameRu;
        this.UNIT_ID = UNIT_ID;
        this.TRANSPORT_TYPE = TRANSPORT_TYPE;
        this.routeId = routeId;
    }

    // Getters for parameters
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

    public void setOnRoute(TransportUnit unit, Route route) {
        routeId = route.ROUTE_ID();
    }

    public int getRoute() {
        return routeId;
    }

    public void relizefromRoute(TransportUnit unit, Route route) {
        routeId = 0;
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
