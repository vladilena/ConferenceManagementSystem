package model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class AttributesManager {
    private static final Logger LOGGER = LogManager.getLogger(AttributesManager.class);

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("attributes");

    private AttributesManager() {
    }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


