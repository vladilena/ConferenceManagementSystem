package model.util;

import java.util.ResourceBundle;

public class PathManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("path_configuration");

    private PathManager() {

    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


