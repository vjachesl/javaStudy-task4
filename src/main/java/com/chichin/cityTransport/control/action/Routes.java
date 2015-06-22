package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.entity.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by viacheslav on 16.06.15.
 */
public class Routes implements Action {

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
