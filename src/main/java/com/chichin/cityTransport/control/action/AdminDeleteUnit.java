package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.TransportUnit;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminDeleteUnit implements Action {
    private static final Logger LOG = Logger.getLogger(AdminDeleteUnit.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "admin_cabinet";
        }
        LOG.debug("was processed with the next param" + request.getParameter("unit_id"));
        TransportUnit unit = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().getTransportUnit(Integer.parseInt(request.getParameter("unit_id")));
        if (unit==null) {
            LOG.debug("dao said - this number is not exists");
            request.getSession().setAttribute("message", "You tried delete not existing Unit! Please reload page!");
            return "admin_cabinet";
        }
        int unitId = Integer.parseInt(request.getParameter("unit_id"));
        int result = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().removeTransportUnit(unitId);
        if (result==0) {
            request.getSession().setAttribute("message", "some error. Unit was n't delete");
            LOG.info("was not delete");
            return "admin_cabinet";
        }
        else {
            request.getSession().setAttribute("message", "Unit was deleted sussesfully!");
            LOG.info("was deleted unit " +unitId + " by user" + request.getSession().getAttribute("admin").toString());
            return "admin_cabinet";
        }
    }
}
