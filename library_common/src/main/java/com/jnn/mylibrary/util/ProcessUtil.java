package com.jnn.mylibrary.util;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/**
 * Author: Sgy
 * Created on: 2017/9/6 15:22
 * Description: 判断当前线程
 */
public class ProcessUtil {
    public static final String APPLICATION_ID = "com.ssyc.WQDriver";

    /**
     * 判断当前进程是否为主线程
     *
     * @param context 上下文
     * @return true 主线程  false 不是主线程
     */
    public static boolean getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        String processName = null;
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                processName = appProcess.processName;
            }
        }
        Log.e("test", "   processName   =  " + processName + "   pid = " + pid + " APPLICATION_ID  =  " + APPLICATION_ID);
        return TextUtils.equals(APPLICATION_ID, processName);
    }
}
