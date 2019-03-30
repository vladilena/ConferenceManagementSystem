package controller.listeners;

import model.entity.User;
import model.util.AttributesManager;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession().getServletContext().removeAttribute(
                (String) se.getSession().getAttribute(AttributesManager.getProperty("user")));
    }
}


