package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import org.apache.log4j.Logger;


public class DataSourceDaoFactory {
    private static final Logger LOG = Logger.getLogger(DataSourceDaoFactory.class);

    public static DaoFactory getDAOFactory() {
        LOG.info("asking for DAO");
        return DaoFactory.getDaoFactory(DaoFactory.ConnTypes.ConnPoolMySql);
    }
}