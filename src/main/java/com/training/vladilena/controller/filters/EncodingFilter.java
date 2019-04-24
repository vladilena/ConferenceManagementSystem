package com.training.vladilena.controller.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code EncodingFilter} class implements {@link Filter} and is used to set
 * {@code UTF-8} character encoding for all requests and responses
 *
 * @author Vladlena Ushakova
 */
public class EncodingFilter implements Filter {
    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(ENCODING);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

