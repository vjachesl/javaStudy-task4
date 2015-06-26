package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.TransportUnit;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class for listing transport unit from system
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class AdminListUnits implements Action {
    private static final Logger LOG = Logger.getLogger(AdminListUnits.class);

    /**
     * Method checks if user logged in,
     * and show all units in the system
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress error or result with result describing
     */

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }

        List<TransportUnit> units = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().getAllTransportUnits();
        if (units == null) {
            LOG.debug("dao said - no transport units");
            request.getSession().setAttribute("message", "there is no transport units!");
            return "admin_result";
        }
        request.getSession().setAttribute("units", units);
        LOG.debug("was formed list of transport units");
        return "admin_units";
    }

}


