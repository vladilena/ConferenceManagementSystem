package model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class RegexManager {
    private static final Logger LOGGER = LogManager.getLogger(RegexManager.class);

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("regex_container");

    private RegexManager() {

    }

    public static String getProperty(String key) {
        LOGGER.info("Before get property from 'regex_container.properties'");

        return resourceBundle.getString(key);
    }
}


