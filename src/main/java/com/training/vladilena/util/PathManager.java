package com.training.vladilena.util;

import java.util.ResourceBundle;

/**
 * The {@code PathManager } class used to obtain the properties
 * by key from {@code "path_configuration.properties"} file which has information
 * about the pages paths
 *
 * @author Vladlena Ushakova
 */
public class PathManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("path_configuration");

    private PathManager() {
    }

    /**
     * Method which is used to get value of the property obtained by the key
     *
     * @param key {@code key} used to find properties
     * @return returns the value of the property obtained by the key
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


