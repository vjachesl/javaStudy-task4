package com.chichin.cityTransport.control.Util;

import com.chichin.cityTransport.control.action.DataSourceDaoFactory;
import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.entity.User;
import org.apache.log4j.Logger;

/**
 * Created by viacheslav on 20.06.15.
 */
public class LoginCheck {
    private static final Logger LOG = Logger.getLogger(LoginCheck.class);

    public static User loginCheck(String login, String pass) {
        LOG.debug("User tryed to login with login: " + login + " and pass " + pass);
        if (login == null || pass == null) return null;
        DaoFactory daoFactory = DataSourceDaoFactory.getDAOFactory();
        User admin = daoFactory.getUserDao().getUser(login, pass);
        LOG.debug("User was autorized: " + (admin != null));
        return admin;
    }
}
