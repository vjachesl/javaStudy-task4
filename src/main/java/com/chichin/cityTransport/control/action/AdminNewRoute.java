package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.Route;
import com.chichin.cityTransport.entity.TransportTypes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chichin.cityTransport.control.Util.InputValidation.*;

/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminNewRoute implements Action {
    private static final Logger LOG = Logger.getLogger(AdminNewRoute.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        LOG.debug("was processed with the next param" +
                request.getParameter("route_id")+
                request.getParameter("route_name_en")+
                request.getParameter("route_name_ru") +
                request.getParameter("route_transp_type") );
        if (isPositiveDecimalNumber((String)  request.getParameter("route_id"))== false ||
            isLatinWord((String)  request.getParameter("route_name_en"))==false ||
            isCyrillicWord((String)  request.getParameter("route_name_ru")) == false ||
            isTransportType((String) request.getParameter("route_transp_type"))==null ){
                request.getSession().setAttribute("message", "You was input invalid parametrs. Please try again!");
                return "admin_new_route";
        }
        Route route = DataSourceDaoFactory.getDAOFactory().getRouteDao().getRoute(Integer.parseInt(request.getParameter("route_id")));
        if (route!=null) {
            LOG.debug("dao said - this number is not empty");
            request.getSession().setAttribute("message", "You was input existing route ID! Please change it!");
            return "admin_new_route";
        }
        int routeId = Integer.parseInt(request.getParameter("route_id"));
        String routeNameRu = request.getParameter("route_name_ru");
        String routeNameEn = request.getParameter("route_name_en");
        TransportTypes type = isTransportType((String) request.getParameter("route_transp_type"));
        int result = DataSourceDaoFactory.getDAOFactory().getRouteDao().addNewRoute(new Route(routeId, routeNameEn, routeNameRu, type));
        if (result!=1) {
            request.getSession().setAttribute("message", "some error. Route was n't add");
            LOG.info("was not updated");
            return "admin_new_route";
        }
        else {
            LOG.info("was added");
            request.getSession().setAttribute("routes", DataSourceDaoFactory.getDAOFactory().getRouteDao().getAllRoutes());
            return "admin_cabinet";
        }
    }

}
