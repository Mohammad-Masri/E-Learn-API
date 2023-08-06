package com.elearn.app.elearnapi.utilities;

import java.util.List;

public class ArrayUtilities {
    public static <T, Y> T findOne(List<T> list, String methodName, Y id) {
        T o = list.stream().filter(obj -> {
            try {
                return obj.getClass().getMethod(methodName).invoke(obj).equals(id);
            } catch (Exception e) {
                return false;
            }
        }).findFirst().orElse(null);
        return o;
    }
}
