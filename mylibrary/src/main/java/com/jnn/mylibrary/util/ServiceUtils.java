package com.jnn.mylibrary.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * 服务工具类
 * Created by sgy on 2016/10/18.
 */
public class ServiceUtils {

    /**
     * 判断服务是否存活
     *
     * @param context     上下文
     * @param serviceName 服务全名称
     * @return true:存活     false：失活
     */
    public static boolean getServiceState(Context context, String serviceName) {
        ActivityManager myAM = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(Integer.MAX_VALUE);
        if (myList != null && myList.size() > 0) {
            for (int i = 0; i < myList.size(); i++) {
                String mName = myList.get(i).service.getClassName().toString();
                if (mName.equals(serviceName)) {
                    return true;
                }
            }
        }
        return false;
    }

}
