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

public class ControlServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ControlServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
    //    response.setContentType("text/html;charset=UTF-8");
       LOG.debug("language is "+  request.getSession().getAttribute("language"));
       LOG.debug("request from path "+  request.getPathInfo());
        String view = null;
        Action action = ActionFactory.getAction(request);
        try {
            view = action.execute(request, response);
            LOG.debug("was executed action = " + view);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            LOG.warn("was formed an exception = " + ex.getMessage());
            view = request.getPathInfo().substring(1);
        }
        dispatch(request, response, view);
    }

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
