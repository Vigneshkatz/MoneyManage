package com.katziio.app.util;

public class CustomUtil {
    public static boolean isValidString(String input) {
        return input != null && !input.isBlank();
    }
    public static boolean isValidObject(Object object) {
        if(object==null) {
            return false;
        }
        if (object instanceof Integer || object instanceof Double || object instanceof Long) {
            return true;
        } else if (object instanceof String) {
            String str = (String) object;
            return !str.isBlank();
        } else {
            return true;
        }
    }
}
