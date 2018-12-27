package com.example.jnn.today;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * 本地音频播放
 */
public class PlayVoice {
    private static MediaPlayer mediaPlayer;

    private static MediaPlayer getInstance(Context context, int resId) {
        synchronized (MediaPlayer.class) {
            mediaPlayer = MediaPlayer.create(context, resId);
            mediaPlayer.start();
        }
        return mediaPlayer;
    }

    public static void playVoice(Context context, int resId) {
        try {
            mediaPlayer = getInstance(context, resId);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //播放完了
                    stopVoice();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //停止播放声音
    public static void stopVoice() {
        if (null != mediaPlayer) {
            mediaPlayer.stop();
        }
    }
}
