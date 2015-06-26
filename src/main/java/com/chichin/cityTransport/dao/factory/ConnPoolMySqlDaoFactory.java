package com.chichin.cityTransport.dao.factory;

import com.chichin.cityTransport.dao.interfaces.RouteDao;
import com.chichin.cityTransport.dao.interfaces.StopsDao;
import com.chichin.cityTransport.dao.interfaces.TransportUnitsDao;
import com.chichin.cityTransport.dao.interfaces.UserDao;
import com.chichin.cityTransport.dao.mySqlDao.MySqlRouteDao;
import com.chichin.cityTransport.dao.mySqlDao.MySqlStopsDao;
import com.chichin.cityTransport.dao.mySqlDao.MySqlTransportUnitsDao;
import com.chichin.cityTransport.dao.mySqlDao.MySqlUserDao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for connection pull operating.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class ConnPoolMySqlDaoFactory extends DaoFactory {

    // store instance of pool
    private static DataSource pool;

    public ConnPoolMySqlDaoFactory() {
        if (pool == null) {
            try {
                Context ctx = new InitialContext();
                pool = (DataSource) ctx.lookup("java:/comp/env/jdbc/My_DB");
            } catch (NamingException e) {
                System.out.println("Can't find context file");
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    @Override
    public RouteDao getRouteDao() {
        return new MySqlRouteDao(this);
    }

    @Override
    public TransportUnitsDao getTransportUnitsDao() {
        return new MySqlTransportUnitsDao(this);
    }

    @Override
    public StopsDao getStopsDao() {
        return new MySqlStopsDao(this);
    }

    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao(this);
    }

}
