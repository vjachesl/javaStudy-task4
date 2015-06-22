package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLogin implements Action {
    private static final Logger LOG = Logger.getLogger(AdminLogin.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.debug("Admin attribut is not session = " + (request.getSession().getAttribute("admin") == null) );
            LOG.debug("processed with admin login page");
            return "admin_login";
        } else {
            LOG.debug("processed with admin cabinet");
            return "admin_cabinet"; //ToDo
        }

    }
}
