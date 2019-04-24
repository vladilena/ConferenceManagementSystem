package com.training.vladilena.util;


import java.util.ResourceBundle;

/**
 * The {@code AttributesManager} class used to obtain the properties
 * by key from {@code "attributes.properties"} file which has information
 * about the attributes
 *
 * @author Vladlena Ushakova
 */
public class AttributesManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("attributes");

    private AttributesManager() {
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


