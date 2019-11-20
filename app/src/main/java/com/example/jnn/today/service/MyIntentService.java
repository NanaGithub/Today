package com.example.jnn.today.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * @author jnn
 * @date 2019/7/5
 * @description 可处理异步请求
 */
public class MyIntentService extends IntentService {

    /**
     * 必须存在无参构造
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * 初始化时开启一个子线程HandlerThread
     */
    @Override
    public void onCreate() {
        Log.i("MyIntentService", "onCreate");
        super.onCreate();
    }

    /**
     * 执行耗时操作
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("MyIntentService", "onHandleIntent");
        int type = intent.getIntExtra("type", 0);
        switch (type) {
            case 1:
                Log.i("MyIntentService", "do task 1");
                break;
            case 2:
                Log.i("MyIntentService", "do task 2");
                break;
            default:
                break;
        }
    }


    /**
     * 将Intent加入到消息队列中，最后按顺序推送到onHandleIntent中处理
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyIntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 当消息队列中消息处理完毕，则自动关闭服务无需手动关闭
     */
    @Override
    public void onDestroy() {
        Log.i("MyIntentService", "onDestroy");
        super.onDestroy();
    }
}
