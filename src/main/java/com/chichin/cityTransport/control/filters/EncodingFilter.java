package com.chichin.cityTransport.control.filters;

/**
 * Created by viacheslav on 13.06.15.
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class EncodingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        LOG.debug("Filter destruction starts");
        // do nothing
        LOG.debug("Filter destruction finished");
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        LOG.debug("EncodingFilter work");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        LOG.debug("Admin status "+httpRequest.getSession().getAttribute("admin"));
        LOG.debug("Requested URI: " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.debug("Requested encoding = null, encoding set to " + encoding);
            request.setCharacterEncoding(encoding);
        }

        LOG.debug("Checking setted locale at session....");
        LOG.debug("locale is "+ request.getLocale());


        LOG.debug("EncodingFilter work finish");



        chain.doFilter(request, response);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("EncodingFilter init");
        encoding = fConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml = " + encoding);
        LOG.debug("EncodingFilter init finished");
    }


}
