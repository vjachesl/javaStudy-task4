package com.chichin.cityTransport.control;


import com.chichin.cityTransport.control.action.Action;
import com.chichin.cityTransport.control.action.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet Class - receives request from user,
 * detect actions and receiving object from action factory
 * after that will dispatch request to resulting page
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class ControlServlet extends HttpServlet {

    // logger object
    private static final Logger LOG = Logger.getLogger(ControlServlet.class);

    /**
     * Method try to detect action, which was came from request adress,
     * then receive class from action factory and runs it and prepare data for result page
     * than ask dispatch method for receiving result page adress and forward user to it
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        LOG.debug("language is " + request.getSession().getAttribute("language"));
        LOG.debug("request from path " + request.getPathInfo());
        String view = null;

        //asking action factory for result
        Action action = ActionFactory.getAction(request);
        try {
            view = action.execute(request, response);
            LOG.debug("was executed action = " + view);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            LOG.warn("was formed an exception = " + ex.getMessage());

            // if no result - just try to transfer to the asking page
            view = request.getPathInfo().substring(1);
        }
        dispatch(request, response, view);
    }

    /**
     * Method forms the result page adress
     * than ask dispatch method for receiving result page adress and forward user to it
     *
     * @param request  the request object for parametrs getting ability
     * @param response the response object for parametrs setting ability
     */

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String view)
            throws ServletException, IOException {
        String prefix = "/view/";
        String suffix = ".jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(prefix + view + suffix);
        LOG.debug("was processed with dispatcher the next page = " + prefix + view + suffix);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
