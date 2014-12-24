package com.exadel.belarusattractions.utils.json;

import com.google.gson.Gson;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * Utility methods.
 * Converts an Object into json String.
 * and then escapes that string.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 5:46 AM, 11/9/12
 */
public final class JsonUtils {

    private JsonUtils() {
    }

    public static String toJsString(Object o) {
        return '"' + StringEscapeUtils.escapeJavaScript(toJson(o)) + '"';
    }

    public static String toJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }
}