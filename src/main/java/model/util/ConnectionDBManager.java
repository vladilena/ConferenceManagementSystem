package model.util;

import java.util.ResourceBundle;

public class ConnectionDBManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("connectionDB");

    private ConnectionDBManager() {

    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
}}


