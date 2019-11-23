package com.day.lib_java.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gson解析工具类
 */
public class GsonUtil {

    public static <T> T fromJson(String json, Type type) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.fromJson(json, type);
    }

    public static <T> List<T> listFromJson(String json) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    public static <T> List<T> listFromJson(String json, Type type) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.fromJson(json, type);
    }

    public static String toJsonString(Object object) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(object);
    }

    /**
     * 如果data是个数组，当data为空时，垃圾服务端不会返回[],而是返回空字符串
     * 他要是不改，那咱们有的是办法
     */
    public static Gson toDataList() {
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {
            @Override
            public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                //假设我们是直接取 data的json串 来反序列化
                if (json.isJsonArray()) {
                    JsonArray array = json.getAsJsonArray();
                    Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                    List list = new ArrayList<>();
                    for (int i = 0; i < array.size(); i++) {
                        JsonElement element = array.get(i);
                        Object item = context.deserialize(element, itemType);
                        list.add(item);
                    }
                    return list;
                } else {
                    //和接口类型不符，返回空List
                    return Collections.EMPTY_LIST;
                }
            }
        }).create();
        return gson;
    }
}