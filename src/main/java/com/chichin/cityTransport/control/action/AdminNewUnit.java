package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.TransportTypes;
import com.chichin.cityTransport.entity.TransportUnit;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chichin.cityTransport.control.Util.InputValidation.*;


/**
 * Created by viacheslav on 20.06.15.
 */
public class AdminNewUnit implements Action {
    private static final Logger LOG = Logger.getLogger(AdminNewUnit.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            LOG.warn("User not autorized");
            return "error_page";
        }
        LOG.debug("was processed with the next param" +
                request.getParameter("unit_id")+ " " +
                request.getParameter("unit_model_name_en")+ " " +
                request.getParameter("unit_model_name_ru") + " " +
                request.getParameter("unit_transp_type") );
        if (isPositiveDecimalNumber((String)  request.getParameter("unit_id"))== false ||
                isLatinWord((String) request.getParameter("unit_model_name_en"))==false ||
                isCyrillicWord((String) request.getParameter("unit_model_name_ru")) == false ||
                isTransportType( request.getParameter("unit_transp_type"))== null ){
            request.getSession().setAttribute("message", "You was input invalid parametrs. Please try again!");
            return "admin_result";
        }
        TransportUnit unit = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().getTransportUnit(Integer.parseInt(request.getParameter("unit_id")));
        if (unit!=null) {
            LOG.debug("dao said - this number is not empty");
            request.getSession().setAttribute("message", "You was input existing unit ID! Please change it!");
            return "admin_result";
        }
        int unitId = Integer.parseInt(request.getParameter("unit_id"));
        String unitNameRu = request.getParameter("unit_model_name_en");
        String unitNameEn = request.getParameter("unit_model_name_ru");
        TransportTypes type = isTransportType( request.getParameter("unit_transp_type"));
        System.out.println(type.toString());
        int result = DataSourceDaoFactory.getDAOFactory().getTransportUnitsDao().addNewTransportUnit(new TransportUnit(unitId, unitNameEn, unitNameRu, type, 0));
        if (result!=1) {
            request.getSession().setAttribute("message", "some error. Unit was n't add");
            LOG.info("Unit was not updated");
            return "admin_result";
        }
        else {
            LOG.info("Unit was added");
            request.getSession().setAttribute("message", " Unit was added");
            return "admin_result";
        }
    }

}
