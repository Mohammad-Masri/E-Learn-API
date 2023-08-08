package com.elearn.app.elearnapi.utilities;

import java.util.List;
import java.util.Set;

public class PrintUtilities {
    public static void println(Object s) {
        System.out.println(s);
    }

    public static void printList(List list) {
        list.forEach((i) -> PrintUtilities.println(i));
    }

    public static void printSet(Set set) {
        set.forEach((i) -> PrintUtilities.println(i));
    }

}
