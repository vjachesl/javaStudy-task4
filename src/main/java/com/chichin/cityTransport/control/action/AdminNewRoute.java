package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.Route;
import com.chichin.cityTransport.entity.TransportTypes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chichin.cityTransport.control.Util.InputValidation.*;

/**
 * Class for new route creation in the system by admin user
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class AdminNewRoute implements Action {

    //logger object
    private static final Logger LOG = Logger.getLogger(AdminNewRoute.class);

    /**
     * Method checks if user logged in,
     * then make input parametrs validation and
     * than make new route in the system.
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress - admin_result with the result of operation showing to user
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // checks if the user logged in
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        LOG.debug("was processed with the next param" +
                request.getParameter("route_id") +
                request.getParameter("route_name_en") +
                request.getParameter("route_name_ru") +
                request.getParameter("route_transp_type"));

        //receiving all inputed parameters and check it fo validity
        if (isPositiveDecimalNumber((String) request.getParameter("route_id")) == false ||
                isLatinWord((String) request.getParameter("route_name_en")) == false ||
                isCyrillicWord((String) request.getParameter("route_name_ru")) == false ||
                isTransportType((String) request.getParameter("route_transp_type")) == null) {
            request.getSession().setAttribute("message", "You was input invalid parametrs. Please try again!");
            return "admin_result";
        }

        // asking DAO for the same route number existing
        Route route = DataSourceDaoFactory.getDAOFactory().getRouteDao().getRoute(Integer.parseInt(request.getParameter("route_id")));
        if (route != null) {
            LOG.debug("dao said - this number is not empty");
            request.setAttribute("message", "You was input existing route ID! Please change it!");
            return "admin_result";
        }

        // preparing parametrs for new route creation and forming result report to the user
        int routeId = Integer.parseInt(request.getParameter("route_id"));
        String routeNameRu = request.getParameter("route_name_ru");
        String routeNameEn = request.getParameter("route_name_en");
        TransportTypes type = isTransportType((String) request.getParameter("route_transp_type"));
        int result = DataSourceDaoFactory.getDAOFactory().getRouteDao().addNewRoute(new Route(routeId, routeNameEn, routeNameRu, type));
        if (result != 1) {
            request.setAttribute("message", "some error. Route was n't add");
            LOG.info("was not updated");
            return "admin_result";
        } else {
            LOG.info("was added");
            request.setAttribute("message", "Route was added");
            request.setAttribute("routes", DataSourceDaoFactory.getDAOFactory().getRouteDao().getAllRoutes());
            return "admin_result";
        }
    }

}
