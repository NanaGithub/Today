package com.example.jnn.today.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jnn.today.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AActivity extends AppCompatActivity {
    private static final String TAG = AActivity.class.getSimpleName();
    private EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Log.e(TAG, "onCreate");
        etText = findViewById(R.id.et_text);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //清单文件中配置了configChanges:orientation|screenSize属性，则该方法才会被调用
        //如果没有配置，则不会走该方法，activity会被销毁重建
        Log.e(TAG, "onConfigurationChanged");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //用户自定义 保存输入框的文字
        String s = etText.getText().toString();
        outState.putString("et", s);
        //调用父类交给系统处理，这样系统能保存视图层次结构状态
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //总是调用超类，以便它可以恢复视图层次超级
        super.onRestoreInstanceState(savedInstanceState);
        String et = savedInstanceState.getString("et");
        if (etText != null)
            etText.setText(et);
        Log.e(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //A收到的B的值
        if (resultCode == RESULT_OK) {
            Toast.makeText(AActivity.this, data.getStringExtra("b"), Toast.LENGTH_LONG).show();
        }
        Log.e(TAG, "onActivityResult");
    }

    public void jumpToB(View view) {
        //startActivityForResult(new Intent(AActivity.this, BActivity.class), 1);
        Intent intent = new Intent(AActivity.this, BActivity.class);
        byte[] bytes = new byte[1024 * 1024];
        intent.putExtra("key", bytes);
        startActivity(intent);
    }
}
