package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLogout implements Action {
    private static final Logger LOG = Logger.getLogger(AdminLogout.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.info("Admin was removed from session. session was destroyed");
        request.getSession().setAttribute("admin", null);
        request.getSession(false);
        request.getSession().invalidate();
        return "index"; //Todo - check
    }
}
