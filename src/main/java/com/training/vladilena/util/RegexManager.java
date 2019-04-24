package com.training.vladilena.util;

import java.util.ResourceBundle;

/**
 * The {@code RegexManager} class used to obtain the properties
 * by key from {@code "regex_container.properties"} file which has information
 * about the regex properties
 *
 * @author Vladlena Ushakova
 */
public class RegexManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("regex_container");

    private RegexManager() {
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


