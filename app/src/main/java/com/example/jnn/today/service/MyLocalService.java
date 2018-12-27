package com.example.jnn.today.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * 服务
 */
public class MyLocalService extends Service {
    private static final String TAG = MyLocalService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        Toast.makeText(this, "服务创建了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        Toast.makeText(this, "服务关闭了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG, "onStart");
    }

    /**
     * startService启动方式生命周期
     * onCreate() -- > onStartCommand() -- > onStart()-- > onDestory()
     * 重复调用startService
     * onStartCommand() -- > onStart()
     * <p>
     * 一旦服务开启就跟调用者（开启者）没有任何关系了。开启者退出了，开启者挂了，服务还在后台长期的运行，开启者不能调用服务里面的方法。
     */


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        Toast.makeText(this, "服务开启了", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * bindService生命周期
     * onCreate() -- > onBind()-->onServiceConnected(继承自ServiceConnection) --> onUnbind() -- > onDestory()
     * 重复 bindService
     * onBind() -->onServiceConnected
     * bind的方式开启服务，绑定服务，调用者挂了，服务也会跟着挂掉。绑定者可以调用服务里面的方法。
     */

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        Toast.makeText(this, "服务绑定了", Toast.LENGTH_SHORT).show();
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        Toast.makeText(this, "服务解绑了", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    public interface MyIBinder {
        void invokeMethodInMyService();
    }

    public class MyBinder extends Binder implements MyIBinder {

        public void stopService(ServiceConnection serviceConnection) {
            unbindService(serviceConnection);
        }

        @Override
        public void invokeMethodInMyService() {
            for (int i = 0; i < 20; i++) {
                System.out.println("service is opening");
            }
        }
    }
}
