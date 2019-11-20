package com.example.jnn.today.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jnn.today.R;

/**
 * 用于服务测试
 */
public class ServiceActivity extends AppCompatActivity {

    private Intent service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        service = new Intent(ServiceActivity.this, MyLocalService.class);
    }

    /**
     * 开启服务
     *
     * @param view
     */
    public void start(View view) {
        startService(service);
    }

    /**
     * 关闭服务
     *
     * @param view
     */
    public void close(View view) {
        if (service == null)
            return;
        stopService(service);
    }


    /**
     * 绑定服务
     *
     * @param view
     */
    private MyServiceConnection bindServiceConnection;

    public void bind(View view) {
        bindServiceConnection = new MyServiceConnection();
        bindService(new Intent(ServiceActivity.this, MyLocalService.class),
                bindServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        if (bindServiceConnection == null)
            return;
        unbindService(bindServiceConnection);
    }


    class MyServiceConnection implements ServiceConnection {
        private final String TAG = MyServiceConnection.class.getSimpleName();

        //这里的第二个参数IBinder就是Service中的onBind方法返回的
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            MyLocalService.MyBinder myBinder = (MyLocalService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
        }
    }


    /**
     * IntentService测试
     */
    public void testIntentService(View view) {
        Intent intent = new Intent(ServiceActivity.this, MyIntentService.class);
        intent.putExtra("type", 1);
        startService(intent);

        Intent intent2 = new Intent(ServiceActivity.this, MyIntentService.class);
        intent2.putExtra("type", 2);
        startService(intent2);

        //再次开启1
        startService(intent);
    }
}
