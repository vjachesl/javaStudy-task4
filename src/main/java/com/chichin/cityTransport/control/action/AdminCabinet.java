package com.chichin.cityTransport.control.action;

import static com.chichin.cityTransport.control.Util.ParsingParametrs.*;
import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.entity.Route;
import com.chichin.cityTransport.entity.User;
import static com.chichin.cityTransport.control.Util.LoginCheck.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by viacheslav on 16.06.15.
 */
public class AdminCabinet implements Action {
    private static final Logger LOG = Logger.getLogger(AdminCabinet.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.debug("Admin attribut is not in session = " + (request.getSession().getAttribute("admin") == null));
        if (request.getSession().getAttribute("admin") == null) {
            User user = loginCheck(request.getParameter("login"), request.getParameter("password"));
            if (user == null)  return "error_page";
            request.getSession().setAttribute("admin", user);
            LOG.debug("Useradmin was putted in the session");
        }
        LOG.debug("was processed admin cabinet, because user already logged in");
        request.getSession().setAttribute("routes", DataSourceDaoFactory.getDAOFactory().getRouteDao().getAllRoutes());
        List<String> param = parseParametres(request.getParameterNames());
        for (String str : param) {
         if (str.contains(":")) {
             request.getSession().setAttribute("route_id", str.substring(15));
             LOG.debug("was processed next command " + str.substring(0, 14) + " with route ID " + str.substring(15));
             return str.substring(0,14);
         } else if (param.size()==1) return param.get(0);
        }
        return "admin_cabinet";
    }


}
