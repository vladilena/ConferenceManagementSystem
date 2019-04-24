package com.training.vladilena.controller.listeners;

import com.training.vladilena.model.entity.User;
import com.training.vladilena.util.AttributesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * The {@code SessionListener} class implements {@link HttpSessionListener}
 * and is used to remove user from session and context after session destroying
 *
 * @author Vladlena Ushakova
 */
public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.debug("Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.debug("Session destroyed");
        User user = (User) se.getSession().getAttribute(AttributesManager.getProperty("user"));

        se.getSession().getServletContext().removeAttribute(user.getLogin());
        se.getSession().removeAttribute(AttributesManager.getProperty("user"));
    }
}


