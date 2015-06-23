package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.TransportUnit;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminListUnits implements Action {
    private static final Logger LOG = Logger.getLogger(AdminListUnits.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }

        List<TransportUnit> units = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().getAllTransportUnits();
        if (units==null) {
            LOG.debug("dao said - no transport units");
            request.getSession().setAttribute("message", "there is no transport units!");
            return "admin_result";
        }
        request.getSession().setAttribute("units", units);
        LOG.debug("was formed list of transport units");
        return "admin_units";
    }

}


