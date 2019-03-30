package model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class PathManager {
    private static final Logger LOGGER = LogManager.getLogger(PathManager.class);

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("path_configuration");

    private PathManager() {

    }

    public static String getProperty(String key) {
        LOGGER.info("Before get property from 'path_configuration.properties'");

        return resourceBundle.getString(key);
    }
}


