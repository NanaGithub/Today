package com.example.jnn.today.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author jnn
 * @date 2019/5/7
 * @description
 */
public interface Api {
    @FormUrlEncoded
    @POST
    Call<ResponseBody> login(@Field("name") String name, @Field("pwd") String pwd);

}
