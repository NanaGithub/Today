package com.jnn.mylibrary.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {


    /**
     * string转换为集合
     *
     * @param data  string字符串
     * @param split 分隔符
     * @return 集合
     */
    public static List<String> stringToList(String data, String split) {
        List<String> list = new ArrayList<>();
        if (TextUtils.isEmpty(data))
            return list;
        String[] strings = data.split(split);
        return Arrays.asList(strings);
    }
}
