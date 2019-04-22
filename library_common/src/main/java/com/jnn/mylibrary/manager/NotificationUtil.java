package com.jnn.mylibrary.manager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.Color;

/**
 * 通知栏管理
 */
public class NotificationUtil {
    private static final String NOTIFICATION_CHANNEL_NAME = "BackgroundLocation";
    private android.app.NotificationManager notificationManager = null;
    private Notification.Builder builder = null;
    private Notification notification = null;
    boolean isCreateChannel = false;

    private android.app.NotificationManager getNotificationManager(Context context) {
        if (null == notificationManager) {
            synchronized (NotificationUtil.class) {
                if (notificationManager == null) {
                    notificationManager = (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                }
            }
        }
        return notificationManager;
    }

    /**
     * 创建一个通知
     *
     * @param context   上下文
     * @param smallIcon 通知栏显示图标
     * @param title     通知栏大标题（app名字即可）
     * @param des       描述（比如 正在后台运行）
     * @return
     */
    public Notification buildNotification(Context context, int smallIcon, String title, String des) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            //Android O上对Notification进行了修改，如果设置的targetSDKVersion>=26建议使用此种方式创建通知栏
            notificationManager = getNotificationManager(context);
            String channelId = context.getPackageName();
            if (!isCreateChannel) {
                NotificationChannel notificationChannel = new NotificationChannel(
                        channelId,
                        NOTIFICATION_CHANNEL_NAME,
                        android.app.NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.enableLights(true);//是否在桌面icon右上角展示小圆点
                notificationChannel.setLightColor(Color.BLUE); //小圆点颜色
                notificationChannel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
                notificationManager.createNotificationChannel(notificationChannel);
                isCreateChannel = true;
            }
            builder = new Notification.Builder(context.getApplicationContext(), channelId);
        } else {
            builder = new Notification.Builder(context.getApplicationContext());
        }
        builder.setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(des)
                .setWhen(System.currentTimeMillis());
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification = builder.build();
        } else {
            return builder.getNotification();
        }
        return notification;
    }
}
