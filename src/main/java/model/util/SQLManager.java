package model.util;

import java.util.ResourceBundle;

public class SQLManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQL_statements");

    private SQLManager() {

    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


