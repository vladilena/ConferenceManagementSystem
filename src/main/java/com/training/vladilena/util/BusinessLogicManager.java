package com.training.vladilena.util;

import java.util.ResourceBundle;

/**
 * The {@code BusinessLogicManager} class used to obtain the properties
 * by key from {@code "business_logic.properties"} file which has information
 * about the business logic attributes
 *
 * @author Vladlena Ushakova
 */
public class BusinessLogicManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("business_logic");

    private BusinessLogicManager() {
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




