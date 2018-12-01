package com.example.jnn.today.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * 监听网络变化广播器
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = NetBroadcastReceiver.class.getSimpleName();

    /**
     * 生命周期：如果一个广播处理完onReceive 那么系统将认定此对象将不再是一个活动的对象，也就会finished掉它。
     * 静态注册在Android8.0已经不起作用，部分起作用的查看api
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive");
        if (intent != null && "test".equals(intent.getAction())) {
            String data = intent.getStringExtra("data");
            Toast.makeText(context, "我收到你发的广播了：" + data, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Toast.makeText(context, "系统网络状态改变了：", Toast.LENGTH_SHORT).show();
            ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    Toast.makeText(context, "wifi已连接：", Toast.LENGTH_SHORT).show();
                } else if (networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                    Toast.makeText(context, "以太网已连接：", Toast.LENGTH_SHORT).show();
                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Toast.makeText(context, "手机已连接：", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "网络已经断开：", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
