package controller.filters;


import model.entity.User;
import model.exceptions.PermissionErrorException;
import model.util.AccessManager;
import model.util.AttributesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    private final static Logger LOGGER = LogManager.getLogger(AccessFilter.class);
    private static AccessManager manager;

    public AccessFilter() {
        manager = new AccessManager();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("Start of Access filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        User user = getUserFromRequestSession(request);
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

    private User getUserFromRequestSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = null;
        if (session != null)
            user = (User) session.getAttribute(AttributesManager.getProperty("user"));
        LOGGER.debug("Get user from request: " + user);
        return user;
    }

}


