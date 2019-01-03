package com.jnn.mylibrary.widget;

import android.os.CountDownTimer;

public class MyCountDownTimer extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (countDownTimerLisenter != null)
            countDownTimerLisenter.onTick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        if (countDownTimerLisenter != null)
            countDownTimerLisenter.onFinish();
    }

    public interface CountDownTimerLisenter {
        void onTick(long millisUntilFinished);

        void onFinish();
    }

    public CountDownTimerLisenter countDownTimerLisenter;

    public void setCountDownTimerLisenter(CountDownTimerLisenter countDownTimerLisenter) {
        this.countDownTimerLisenter = countDownTimerLisenter;
    }
}
