package com.day.lib_java.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.Date;
import java.util.List;


/**
 * @author jnn
 * @date 2019/11/20
 * @description Gson学习
 * 推荐文章：https://www.jianshu.com/p/e740196225a4
 */
public class GsonHelper {

    /**
     * 自动序列化&反序列化
     * 基本用法：toJson、fromJson
     */
    public static void basicUsage() {
        Gson gson = new Gson();
        /*1）基本数据类型*/
        String s = gson.fromJson("\"100\"", String.class);
        int i = gson.fromJson("1", int.class);
        double d = gson.fromJson("1.0", double.class);
        boolean b = gson.fromJson("true", boolean.class);
        Float f = gson.fromJson("19.9", Float.class);
        System.out.println(s);
        System.out.println("-----------------");


        gson.toJson("1");
        gson.toJson(true);
        gson.toJson(1.0);
        String[] array = {"序列化", "一个数组"};
        String json = gson.toJson(array);
        System.out.println(json);

        /*2）对象  */
        Dog dog = new Dog("序列化-青椒", "1");
        String toJson = gson.toJson(dog);

        String j = "{\"name\":\"反序列-青椒\",\"age\":\"6\",\"EyesColor\":\"黑色\"}";
        Dog fromJson = gson.fromJson(j, Dog.class);

        System.out.println("---------------");
        System.out.println(toJson + "\n" + fromJson.toString());
    }

    /**
     * 手动序列化&反序列化
     * JsonReader，JsonWriter
     */
    public static void stream() {
        //反序列化「读取服务器返回的数据 反序列化 到具体对象中」
        String json = "{\"name\":\"反序列-青椒\",\"age\":\"6\",\"EyesColor\":\"黑色\"}";
        JsonReader reader = new JsonReader(new StringReader(json));
        Dog dog = new Dog();
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String key = reader.nextName();
                switch (key) {
                    case "name":
                        dog.name = reader.nextString();
                        break;
                    case "age":
                        dog.age = reader.nextString();
                        break;
                    case "EyesColor":
                        dog.eyesColor = reader.nextString();
                        break;
                    default:
                        break;

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(dog.name);
        }

        //序列化「将对象中所有的数据 序列化 成一个json字符串」
        Dog dog1 = new Dog("一只", "12");
        Gson gson = new Gson();
        gson.toJson(dog1, System.out);
        //手动构建一个json
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(System.out));
        try {
            writer.beginObject()
                    .name("name").value("一只狗")
                    .name("age").value("18")
                    .name("eye").value("黑色的大眼睛")
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //记得刷新
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 数组、集合
     */
    public static void arrayUsage() {
        Gson gson = new Gson();
        String array = "[\"Android\",\"Java\",\"Flutter\"]";
        //反序列化到数组中
        String[] strings = gson.fromJson(array, String[].class);
        //反序列化到集合中
        List<String> list = gson.fromJson(array, List.class);

        /**
         * 反序列化到集合中，在Android应用中，我们通常会将服务器返回的数据反射到集合中，更便于操作。
         *
         * 对于Java来说List<String> 和List<Dog>
         * 这俩个的字节码文件只一个那就是List.class，
         * 这是Java泛型使用时要注意的问题 泛型擦除。
         *
         * 所以，如果要反射到具体的集合对象中，List.class是不行的
         * 因此Gson就创造了TypeToken解决泛型擦除问题
         */
        String arrayObject = "[{\"name\":\"反序列-青椒\",\"age\":\"6\",\"EyesColor\":\"黑色\"}]";
        //错误
        //List<Dog> dogList = gson.fromJson(arrayObject, List.class);
        //正确
        List<Dog> dogList = gson.fromJson(arrayObject, new TypeToken<List<Dog>>() {
        }.getType());
        System.out.println(dogList.get(0).name);
    }


    /**
     * 注解与GsonBuilder配合使用
     * GsonBuilder是Gson的升级类，用GsonBuilder来使用解析数据使用更多的小技巧
     */
    public static void builder() {
        Gson gson = new GsonBuilder()
                //针对序列化，默认不输入 value=null 的key，设置该属性会输出。
                .serializeNulls()
                //针对序列化，格式化输出json
                .setPrettyPrinting()
                //设置日期格式，只针对日期属性，对long等自以为的日期转换无效
                .setDateFormat("yyyy-MM-dd HH：mm：ss")
                //禁止转移html标签「使用场景：希望得到原始html标签」
                .disableHtmlEscaping()
                //配合注解 @Expose 一起使用才能生效「也没什么需求场景感觉」
                .excludeFieldsWithoutExposeAnnotation()
                //配合注解  @Since「当这里设置的版本大于注解中的」 和 @Until「当这里设置的版本小于等于注解中的」
                //一起使用才能生效「感觉可以用于服务器api升级中，会更新字段，这里去注解，更清楚」
                .setVersion(1.0)
                .create();
        gson.toJson(new Dog("狗狗", "18"), System.out);
        gson.toJson(new Dog("狗狗", "<div>原型输出</div>", new Date()), System.out);

    }

    /**
     * 自己接管gson解析过程
     * TypeAdapter,JsonSerializer,JsonDeserializer
     */
    public static void typeAdapter() {
        //注册优先级最高「使用场景，想要重新构建」
        Gson gson = new GsonBuilder()
                //注册TypeAdapter，自己想解析成啥样就解析成啥样
                .registerTypeAdapter(Dog.class, new TypeAdapter<Dog>() {
                    @Override
                    public void write(JsonWriter out, Dog value) throws IOException {
                        //序列化,当外部调用序列化一个对象时，可以在这里重新构建key-value
                        out.beginObject()
                                .name("person").value(value.name)
                                .name("city").value(value.age)
                                .endObject();
                    }

                    @Override
                    public Dog read(JsonReader in) throws IOException {
                        //反序列化
                        //映射到自己想要映射的实体类中
                        Dog dog1 = new Dog();
                        while (in.hasNext()) {
                            String s = in.nextName();
                            switch (s) {
                                case "person":
                                    dog1.name = in.nextString();
                                    break;
                            }
                        }
                        return dog1;
                    }
                })
                .create();
        gson.toJson(new Dog("哈哈哈哈", "1"), System.out);


        //案例：使用场景「服务器返回的数据，我们直接用int接收，那么当无数据时返回 空字符""，就会报错」
        new GsonBuilder()
                //解决方案，注册一个，自己手动处理
                .registerTypeAdapter(Integer.class, new TypeAdapter<Integer>() {

                    @Override
                    public void write(JsonWriter out, Integer value) throws IOException {
                        out.value(String.valueOf(value));
                    }

                    @Override
                    public Integer read(JsonReader in) throws IOException {
                        //当返回空字符串时，我们直接返回-1
                        try {
                            return Integer.parseInt(in.nextString());
                        } catch (NumberFormatException e) {
                            return -1;
                        }
                    }
                })
                .create();
    }

    public static void main(String[] args) {
        //basicUsage();
        //arrayUsage();
        //stream();
        //builder();
        typeAdapter();
    }
}
