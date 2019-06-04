package com.example.jnn.today;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Linz
 * @date 2019/5/18
 * @description
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 ARouter
        ARouter.init(this);

    }
}
