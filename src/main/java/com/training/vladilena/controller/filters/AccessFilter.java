package com.training.vladilena.controller.filters;

import com.training.vladilena.controller.command.GenerateUser;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.exceptions.PermissionErrorException;
import com.training.vladilena.util.AccessManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * The {@code AccessFilter} class implements {@link Filter}, {@link GenerateUser}
 * and is used to check if user has a permission to see a page or execute a command
 *
 * @author Vladlena Ushakova
 */
public class AccessFilter implements Filter, GenerateUser {
    private final static Logger LOGGER = LogManager.getLogger(AccessFilter.class);
    private static AccessManager manager;

    public AccessFilter() {
        manager = new AccessManager();
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("Start of Access filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        User user = getUserFromSession(request);
        String userRole = null;
        String email = null;
        if (user != null) {
            userRole = user.getRole().toString();
            email = user.getEmail();
        }
        String command = request.getParameter("action");
        if (manager.isSecuredPage(uri)) {
            boolean hasPermission = manager.checkPermission(uri, userRole, command);
            if (!hasPermission) {
                LOGGER.error("Unauthorized access, email: " + (email == null ? "unregistered" : email) + ", role: " + userRole);
                throw new PermissionErrorException("Unauthorized access");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}


