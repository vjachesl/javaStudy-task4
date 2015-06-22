package com.chichin.cityTransport.dao.factory;


import com.chichin.cityTransport.dao.interfaces.RouteDao;
import com.chichin.cityTransport.dao.interfaces.StopsDao;
import com.chichin.cityTransport.dao.interfaces.TransportUnitsDao;
import com.chichin.cityTransport.dao.interfaces.UserDao;
import com.chichin.cityTransport.dao.mySqlDao.MySqlRouteDao;
import com.chichin.cityTransport.dao.mySqlDao.MySqlStopsDao;
import com.chichin.cityTransport.dao.mySqlDao.MySqlTransportUnitsDao;
import com.chichin.cityTransport.dao.mySqlDao.MySqlUserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerMySqlDaoFactory extends DaoFactory {

    @Override
    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/city_transport_db";
        String user = "root";
        String pass = "sys";
        return DriverManager.getConnection(url, user, pass);
    }

    @Override
    public RouteDao getRouteDao() {
        return new MySqlRouteDao(this);
    }

    @Override
    public StopsDao getStopsDao() {
        return new MySqlStopsDao(this);
    }

    @Override
    public TransportUnitsDao getTransportUnitsDao() {
        return new MySqlTransportUnitsDao(this);
    }

    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao(this);
    }

}
