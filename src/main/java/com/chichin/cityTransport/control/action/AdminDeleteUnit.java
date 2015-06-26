package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.TransportUnit;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chichin.cityTransport.control.Util.InputValidation.isPositiveDecimalNumber;

/**
 * Class for deleting transport unit from system
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public class AdminDeleteUnit implements Action {
    // logger object
    private static final Logger LOG = Logger.getLogger(AdminDeleteUnit.class);

    /**
     * Method checks if user logged in,
     * checks if it receive correct parametr and Transport unit existing
     * and delete it
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress error or result with result describing
     */

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // checks if the user logged in
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        //receiving unitId parametr and check if it valid number
        LOG.debug("was processed with the next param" + request.getParameter("unit_id"));
        if (isPositiveDecimalNumber((String) request.getParameter("unit_id")) == false) {
            request.getSession().setAttribute("message", "You was input invalid parametrs. Please try again!");
            return "admin_result";
        }
        // Checking valid transport unit from DAO
        TransportUnit unit = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().getTransportUnit(Integer.parseInt(request.getParameter("unit_id")));
        if (unit == null) {
            LOG.debug("dao said - this number is not exists");
            request.setAttribute("message", "You tried delete not existing Unit! Please reload page!");
            return "admin_result";
        }
        // deleting transport unit and analysing results
        int unitId = Integer.parseInt(request.getParameter("unit_id"));
        int result = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().removeTransportUnit(unitId);
        if (result == 0) {
            request.setAttribute("message", "some error. Unit was n't delete");
            LOG.info("was not delete");
            return "admin_result";
        } else {
            request.setAttribute("message", "Unit was deleted sussesfully!");
            LOG.info("was deleted unit " + unitId + " by user" + request.getSession().getAttribute("admin").toString());
            return "admin_result";
        }
    }
}
