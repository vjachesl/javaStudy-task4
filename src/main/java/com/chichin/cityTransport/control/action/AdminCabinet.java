package com.chichin.cityTransport.control.action;

import static com.chichin.cityTransport.control.Util.InputValidation.isPositiveDecimalNumber;
import static com.chichin.cityTransport.control.Util.ParsingParametrs.*;

import com.chichin.cityTransport.entity.Stop;
import com.chichin.cityTransport.entity.TransportUnit;
import com.chichin.cityTransport.entity.User;

import static com.chichin.cityTransport.control.Util.LoginCheck.*;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class make all does connected with admin does.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 16, 2015.
 */
public class AdminCabinet implements Action {
    // logger object
    private static final Logger LOG = Logger.getLogger(AdminCabinet.class);

    /**
     * Method checks if user logged in, try to log it with login and password
     * and prepare info for jsp
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next action - was depend on incoming command and user state
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        LOG.debug("Admin attribut is not in session = " + (request.getSession().getAttribute("admin") == null));

        // This section checks if the user has rights for making actions for administrators
        if (request.getSession().getAttribute("admin") == null) {
            // if user ==null - called method, which checks, if user exists with the provided login and password
            User user = loginCheck(request.getParameter("login"), request.getParameter("password"));
            if (user == null) return "error_page";
            // if user valid - it will be putted into session
            request.getSession().setAttribute("admin", user);
            LOG.debug("Useradmin was putted in the session");
        }

        LOG.debug("was processed admin cabinet, because user already logged in");

        // Get All nessessary Data - will be automaticaly update after some does like adding or deleting

        request.getSession().setAttribute("routes", DataSourceDaoFactory.getDAOFactory().getRouteDao().getAllRoutes());
        request.getSession().setAttribute("units", DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().getAllTransportUnits());
        request.getSession().setAttribute("stops", DataSourceDaoFactory.getDAOFactory().getStopsDao().getAllStops());

        // section for additional command parsing (command like assign:101)
        List<String> param = parseParametres(request.getParameterNames());
        for (String str : param) {
            if (str.contains(":")) {
                if (isPositiveDecimalNumber(str.substring(15)) == true) {

                    // if such command registered, asks dao for additional information for finded route_id
                    List<Stop> routeStops = DataSourceDaoFactory.getDAOFactory().getStopsDao().getRouteStops(Integer.parseInt(str.substring(15)));
                    List<Stop> allStops = DataSourceDaoFactory.getDAOFactory().getStopsDao().getAllStops();
                    List<TransportUnit> routeTransportUnits = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().getRouteTransportUnits(Integer.parseInt(str.substring(15)));
                    List<TransportUnit> routeZeroTransportUnits = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().getRouteTransportUnits(0);
                    //putting all info into session
                    request.getSession().setAttribute("route_units", routeTransportUnits);
                    request.getSession().setAttribute("zero_units", routeZeroTransportUnits);
                    request.getSession().setAttribute("route_stops", routeStops);
                    request.getSession().setAttribute("all_stops", allStops);
                    request.getSession().setAttribute("route_id", str.substring(15));
                }


                LOG.debug("was processed next command " + str.substring(0, 14) + " with route ID " + str.substring(15));
                // retuns parsed command for asking needed jsp for preparing ifo for servlet action
                return str.substring(0, 14);
                // else returns another command
            } else if (param.size() == 1) return param.get(0);
        }
        // if no param - return cabinet
        return "admin_cabinet";
    }


}
