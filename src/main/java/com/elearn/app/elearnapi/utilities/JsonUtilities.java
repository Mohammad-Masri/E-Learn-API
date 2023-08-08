package com.elearn.app.elearnapi.utilities;

import com.google.gson.Gson;

public class JsonUtilities {
    public static String convertToJson(Object o) {
        return new Gson().toJson(o);
    }
}
