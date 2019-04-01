package controller.filters;


import model.entity.User;
import model.exceptions.PermissionErrorException;
import model.util.AccessManager;
import model.util.AttributesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter extends AbstractFilter {
    private final static Logger LOGGER = LogManager.getLogger(AccessFilter.class);

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("Start of Access filter");
        String uri = request.getRequestURI();
        User user = getUserFromRequestSession(request);
        String userRole = null;
        if (user != null) {
            userRole = user.getRole().toString();
        }

        AccessManager manager = new AccessManager();

        if (manager.isSecuredPage(uri)) {
            boolean hasPermission = manager.checkPermission(uri, userRole);
            if (!hasPermission) {
                String email = user.getEmail();
                LOGGER.error("Unauthorized access, email: " + (email == null ? "unregistered" : email));
                throw new PermissionErrorException();
            }
        }
        filterChain.doFilter(request, response);
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


