package me.kyunghwan.review.util;

import com.google.gson.JsonObject;

public class JsonUtils {

    public static String toJson(String key, String value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(key, value);
        return jsonObject.toString();
    }

}
