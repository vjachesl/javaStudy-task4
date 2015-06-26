package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import org.apache.log4j.Logger;

/**
 * Class for receiving DAO factory with the using TOMCAT connection pool
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class DataSourceDaoFactory {
    private static final Logger LOG = Logger.getLogger(DataSourceDaoFactory.class);

    public static DaoFactory getDAOFactory() {
        LOG.info("asking for DAO factory");
        return DaoFactory.getDaoFactory(DaoFactory.ConnTypes.ConnPoolMySql);
    }
}