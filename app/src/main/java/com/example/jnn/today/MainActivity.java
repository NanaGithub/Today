package com.example.jnn.today;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jnn.today.broadcast.NetBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private NetBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 动态注册广播：在activity结束前，移除广播接收器。
     *
     * @param view
     */
    public void registerBroadcast(View view) {
        receiver = new NetBroadcastReceiver();
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    /**
     * 发送广播
     *
     * @param view
     */
    public void sendBroadcast(View view) {
        //系统广播不允许发送
        Intent intent = new Intent("test");
        intent.putExtra("data", "你真好看");
        sendBroadcast(intent);
    }
}
