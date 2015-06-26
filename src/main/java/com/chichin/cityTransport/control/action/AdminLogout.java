package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class for removing admin  from the session
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class AdminLogout implements Action {

    //logger object
    private static final Logger LOG = Logger.getLogger(AdminLogout.class);

    /**
     * Method destroys admin object in the session,
     * then will destroy session
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress - returns to the main page
     */

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.info("Admin was removed from session. session was destroyed");
        request.getSession().setAttribute("admin", null);
        request.getSession(false);
        request.getSession().invalidate();
        return "index"; //Todo - check what is nessessary method for destroing session
    }
}
