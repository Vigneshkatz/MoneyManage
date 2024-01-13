package com.katziio.app.util;

import com.katziio.app.util.enums.ExpenseCategory;

import java.util.Locale;

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
    public static ExpenseCategory getCategory(String input) {
        switch (input.toUpperCase()) {
            case "HEALTHWELLNESS":
                return ExpenseCategory.HEALTH_WELLNESS;
            case "HOBBIES":
                return ExpenseCategory.HOBBIES;
            case "TRAVEL":
                return ExpenseCategory.TRAVEL;
            case "HOMEMAINTENANCE":
                return ExpenseCategory.HOME_MAINTENANCE;
            case "EDUCATION":
                return ExpenseCategory.EDUCATION;
            case "TECHNOLOGY":
                return ExpenseCategory.TECHNOLOGY;
            case "FOODDINING":
                return ExpenseCategory.FOOD_DINING;
            case "CLOTHINGACCESSORIES":
                return ExpenseCategory.CLOTHING_ACCESSORIES;
            case "GIFTSCELEBRATIONS":
                return ExpenseCategory.GIFTS_CELEBRATIONS;
            default:
                return ExpenseCategory.ENTERTAINMENT;
        }
    }
}
