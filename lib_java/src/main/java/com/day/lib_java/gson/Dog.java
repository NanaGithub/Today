package com.day.lib_java.gson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Dog {
    /**
     * @SerializedName("服务器返回的key")：该注解用于规范命名
     */
    @SerializedName("dog_color")
    String dogColor;

    @SerializedName(value = "eyesColor", alternate = {"eyes_color", "EyesColor"})
    String eyesColor;

    /**
     * @Expose 序列化时生效
     */
    @Expose(deserialize = false, serialize = true)
    Dog parent;

    String name;
    String age;
    Date date;


    public Dog() {
    }

    public Dog(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Dog(String name, String age, Date date) {
        this.name = name;
        this.age = age;
        this.date = date;
    }


    @Override
    public String toString() {
        return "{name=" + name + ",age=" + age + ",eyesColor=" + eyesColor +
                "}";
    }
}
