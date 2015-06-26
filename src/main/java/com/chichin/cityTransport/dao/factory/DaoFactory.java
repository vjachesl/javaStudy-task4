package com.chichin.cityTransport.dao.factory;


import com.chichin.cityTransport.dao.interfaces.RouteDao;
import com.chichin.cityTransport.dao.interfaces.StopsDao;
import com.chichin.cityTransport.dao.interfaces.TransportUnitsDao;
import com.chichin.cityTransport.dao.interfaces.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for choice connection types
 * and returns needed DAO FACTORY.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */


public abstract class DaoFactory {

    public enum ConnTypes {
        DriverManagerMySql,
        ConnPoolMySql
    }

    public static DaoFactory getDaoFactory(ConnTypes connType) {
        switch (connType) {
            case DriverManagerMySql:
                return new DriverManagerMySqlDaoFactory();
            case ConnPoolMySql:
                return new ConnPoolMySqlDaoFactory();
            default:
                return null;
        }
    }

    public abstract Connection getConnection() throws SQLException;

    public abstract TransportUnitsDao getTransportUnitsDao();

    public abstract RouteDao getRouteDao();

    public abstract StopsDao getStopsDao();

    public abstract UserDao getUserDao();

}
