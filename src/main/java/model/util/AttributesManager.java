package model.util;

import java.util.ResourceBundle;

public class AttributesManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("attributes");

    private AttributesManager() {
    }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


