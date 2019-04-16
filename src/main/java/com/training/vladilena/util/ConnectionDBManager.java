package com.training.vladilena.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;
/**
 * The {@code ConnectionDBManager} class used to obtain the properties
 * by key from {@code "connectionDB.properties"} file which has information
 * about the connection to a database
 *
 * @author Vladlena Ushakova
 */
public class ConnectionDBManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("connectionDB");
    private ConnectionDBManager() {}
    /**
     * Method which is used to get value of the property obtained by the key
     *
     * @param key {@code key} used to find properties
     * @return returns the value of the property obtained by the key
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
}}


