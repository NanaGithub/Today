package com.jnn.mylibrary.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.jnn.mylibrary.manager.ActivityManager;

/**
 * 通用基类Activity，可以继承该类定制AppActivity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected static final String TAG = BaseActivity.class.getSimpleName();
    protected Context mContext;
    protected LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        this.mInflater = LayoutInflater.from(mContext);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //强制竖屏
        ActivityManager.getScreenManager().addActivity(this); //activity管理

    }

    /**
     * 启动activity
     *
     * @param targetActivity 目标activity
     */
    protected void enterActivity(Class<?> targetActivity) {
        startActivity(new Intent(this, targetActivity));
    }

    /**
     * 带有参数的启动activity
     *
     * @param bundle         参数
     * @param targetActivity 目标activity
     */
    protected void enterActivity(Bundle bundle, Class<?> targetActivity) {
        if (bundle == null)
            throw new NullPointerException("param bundle is null");
        Intent intent = new Intent(this, targetActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 查找控件
     */
    protected <T extends View> T find(int viewId) {
        return (T) findViewById(viewId);
    }

    /**
     * 快速点击判断
     */
    private long lastClick = 0;

    protected boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500) {
            lastClick = now;
            return false;
        }
        return true;
    }

    /**
     * 按两次退出应用
     */
    private long exitTime = 0;

    protected void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
