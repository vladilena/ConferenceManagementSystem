package controller.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Locale;

public class LocaleListener implements HttpSessionAttributeListener {
    private static final Logger LOGGER = LogManager.getLogger(LocaleListener.class);
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        if (se.getName().equals("language")) {
            LOGGER.debug("Get local string from request: "+se.getSession().getAttribute("language"));
            String locale = String.valueOf(se.getSession().getAttribute("language"));
            LOGGER.debug("Set attribute locale: " + locale.substring(0, 2)+"_" +locale.substring(3, 5));
            se.getSession().setAttribute("locale",
                    new Locale(locale.substring(0, 2), locale.substring(3, 5)));
        }
    }
}


