package controller.listeners;

import model.entity.User;
import model.util.AttributesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.debug("Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.debug("Session destroyed");
        se.getSession().getServletContext().removeAttribute((AttributesManager.getProperty("user")));
    }
}


