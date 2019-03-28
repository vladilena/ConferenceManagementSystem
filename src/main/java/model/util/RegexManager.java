package model.util;

import java.util.ResourceBundle;

public class RegexManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("regex_container");

    private RegexManager() {

    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


