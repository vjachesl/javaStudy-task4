package com.chichin.cityTransport.control.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class for checking if admin is already logged in the  system
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class AdminLogin implements Action {

    //logger object
    private static final Logger LOG = Logger.getLogger(AdminLogin.class);

    /**
     * Method checks if user logged in,
     * then transfer it in the cabinet - if no - asks logina and password page
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.debug("Admin attribut is not session = " + (request.getSession().getAttribute("admin") == null));
            LOG.debug("processed with admin login page");
            return "admin_login";
        } else {
            LOG.debug("processed with admin cabinet");
            return "admin_cabinet";
        }

    }
}
