package com.chichin.cityTransport.dao.mySqlDao;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.entity.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by viacheslav on 16.06.15.
 */
public class MySqlUserDaoTest {

    @Test
    public void testGetUser() throws Exception {
        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.ConnTypes.DriverManagerMySql);
        User user = daoFactory.getUserDao().getUser("admin", "admin");
        assertEquals((user.getLogin()), "admin");
    }


}