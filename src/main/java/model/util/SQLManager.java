package model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class SQLManager {
    private static final Logger LOGGER = LogManager.getLogger(SQLManager.class);

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQL_statements");

    private SQLManager() {

    }

    public static String getProperty(String key) {

        return resourceBundle.getString(key);
    }
}


