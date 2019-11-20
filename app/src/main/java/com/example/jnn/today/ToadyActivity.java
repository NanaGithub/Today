package com.example.jnn.today;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Lambda表达式
 */
public class ToadyActivity extends AppCompatActivity {
    public static final String TAG = ToadyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toady);
        Button btn = findViewById(R.id.btn);

        //以前这么写
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //现在这么写
        btn.setOnClickListener(v -> Log.e(TAG, "java8这样写"));


        //lambda表达式没有名字，那我们怎么知道它的类型呢？答案是通过上下文推导而来的。
        View.OnClickListener clickListener = (View v) -> {
        };


        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        Runnable runnable1 = () -> doSomthing();

    }

    private void doSomthing() {

    }
}
