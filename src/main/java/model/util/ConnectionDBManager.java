package model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class ConnectionDBManager {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionDBManager.class);

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("connectionDB");

    private ConnectionDBManager() {

    }

    public static String getProperty(String key) {
        LOGGER.info("Before get property from 'connectionDB.properties'");

        return resourceBundle.getString(key);
}}


