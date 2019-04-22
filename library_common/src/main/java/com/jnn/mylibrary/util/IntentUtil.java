package com.jnn.mylibrary.util;

import android.content.Intent;
import android.net.Uri;

/**
 * 系统Intent工具类
 */
public class IntentUtil {

    /**
     * 跳转到拨号界面，同时传递电话号码
     *
     * @param phoneNumber 手机号
     * @return Intent
     */
    public static Intent getCallPhoneIntent(String phoneNumber) {
        return new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
    }
}
