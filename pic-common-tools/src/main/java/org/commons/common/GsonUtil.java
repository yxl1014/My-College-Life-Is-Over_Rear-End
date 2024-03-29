package org.commons.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yxl17
 * @Package : common
 * @Create on : 2023/12/22 00:00
 **/
public class GsonUtil {
    private static final Gson GSON = new Gson();

    public static <T> String toJson(T t) {
        return GSON.toJson(t);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static <T> List<T> listFromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    public static class Test {
        int id;
        String name;

        public Test(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        list.add(new Test(1, "1"));
        list.add(new Test(2, "2"));
        list.add(new Test(3, "3"));
        list.add(new Test(4, "4"));
        list.add(new Test(5, "5"));
        System.out.println(list);
        String json = toJson(list);
        System.out.println(json);
        List<Test> tasks = listFromJson(json, Test.class);
        System.out.println(tasks);
    }
}
