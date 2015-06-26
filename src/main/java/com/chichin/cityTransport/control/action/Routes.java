package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.entity.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class for preparing data for routes.jsp
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class Routes implements Action {
    /**
     * Method receive all info about route from DAO,
     * then put all info into session and transfer user to the jsp
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     * @return String - next jsp adress - admin_result with the result of operation showing to user
     */

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("routes") != null) return "routes";
        else {
            DaoFactory daoFactory = DataSourceDaoFactory.getDAOFactory();
            List<Route> allRoutes = daoFactory.getRouteDao().getAllRoutes();
            if (allRoutes == null) {
                request.getSession().setAttribute("error", "Error with DB connection");
                return "error_page";
            }
            request.getSession().setAttribute("routes", allRoutes);
            return "routes";
        }
    }
}
