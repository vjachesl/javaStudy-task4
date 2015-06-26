package com.chichin.cityTransport.control.action;

import com.chichin.cityTransport.entity.Stop;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chichin.cityTransport.control.Util.InputValidation.*;

/**
 * Class for new stop adding to the system
 * new stop will be added into route units
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public class AdminNewStop implements Action {
    // logger object
    private static final Logger LOG = Logger.getLogger(AdminNewStop.class);

    /**
     * Method checks if user logged in,
     * then make input parametrs validation and
     * than if stop not exists in the system, add new stop.
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
                request.getParameter("stop_id") +
                request.getParameter("stop_name_en") +
                request.getParameter("stop_name_ru") +
                request.getParameter("stop_lat") +
                request.getParameter("stop_long"));
        //receiving all inputed parameters and check it fo validity
        if (isPositiveDecimalNumber((String) request.getParameter("stop_id")) == false ||
                isLatinWord((String) request.getParameter("stop_name_en")) == false ||
                isCyrillicWord((String) request.getParameter("stop_name_ru")) == false ||
                isFilled((String) request.getParameter("stop_lat")) == true ||
                isFilled((String) request.getParameter("stop_long")) == true) {
            request.getSession().setAttribute("message", "You was input invalid parametrs. Please try again!");
            return "admin_result";
        }
        // asking DAO for the same stop number existing
        Stop stop = DataSourceDaoFactory.getDAOFactory().getStopsDao().getStop(Integer.parseInt(request.getParameter("stop_id")));
        if (stop != null) {
            LOG.debug("dao said - this number is not empty");
            request.getSession().setAttribute("message", "You was input existing stop ID! Please change it!");
            return "admin_result";
        }
        // preparing parametrs for new stop creation and forming result report to the user
        int stopId = Integer.parseInt(request.getParameter("stop_id"));
        String stopNameRu = request.getParameter("stop_name_ru");
        String stopNameEn = request.getParameter("stop_name_en");
        Double stopLat = Double.valueOf(request.getParameter("stop_lat"));
        Double stopLong = Double.valueOf(request.getParameter("stop_long"));
        int result = DataSourceDaoFactory.getDAOFactory().getStopsDao().addNewStop(new Stop(stopId, stopNameEn, stopNameRu, stopLat, stopLong));
        if (result != 1) {
            request.getSession().setAttribute("message", "some error. Stop was n't add");
            LOG.info("was not updated");
            return "admin_result";
        } else {
            LOG.info("was added");
            request.getSession().setAttribute("message", "was added");
            return "admin_result";
        }
    }

}
