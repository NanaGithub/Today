package com.example.jnn.today.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jnn.today.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 简单的retrofit网络请求步骤
 * 1，定义网络请求的Api接口 {@link Api}
 * 2，初始化retrofit实例
 * 3，生成接口实现类
 * 4，调用接口方法，发起请求
 */
public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        //2,
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("基础链接")
                .build();
        //3,
        Api api = retrofit.create(Api.class);

        Call<ResponseBody> call = api.login("Tom", "1234");
        //4，
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        call.request();

    }
}
