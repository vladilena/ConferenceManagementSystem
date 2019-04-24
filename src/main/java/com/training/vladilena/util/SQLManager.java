package com.training.vladilena.util;

import java.util.ResourceBundle;

/**
 * The {@code SQLManager} class used to obtain the properties
 * by key from {@code "SQL_statements.properties"} file which has information
 * about the sql queries
 *
 * @author Vladlena Ushakova
 */
public class SQLManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQL_statements");

    private SQLManager() {
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


