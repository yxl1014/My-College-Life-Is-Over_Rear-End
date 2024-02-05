package org.commons.common;

import com.google.gson.Gson;

/**
 * @author yxl17
 * @Package : common
 * @Create on : 2023/12/22 00:00
 **/
public class GsonUtil {
    private static final Gson GSON =new Gson();

    public static<T> String toJson(T t){
        return GSON.toJson(t);
    }
    public static<T> T fromJson(String json,Class<T> clazz){
        return GSON.fromJson(json,clazz);
    }
}
