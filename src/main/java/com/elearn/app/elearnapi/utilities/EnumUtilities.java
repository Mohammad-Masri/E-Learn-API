package com.elearn.app.elearnapi.utilities;

public class EnumUtilities {
    public static <T extends Enum<T>> String convertEnumValuesToString(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        StringBuilder enumString = new StringBuilder();

        for (int i = 0; i < enumConstants.length; i++) {
            enumString.append(enumConstants[i]);
            if (i < enumConstants.length - 1) {
                enumString.append(", ");
            }
        }

        return enumString.toString();
    }
}
