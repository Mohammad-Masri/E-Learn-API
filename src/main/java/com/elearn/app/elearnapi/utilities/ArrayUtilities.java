package com.elearn.app.elearnapi.utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    public static <T> LinkedList<T> convertSetToLinkedList(Set<T> set) {
        return new LinkedList<>(set);
    }
}
