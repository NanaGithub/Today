package com.jnn.mylibrary.util;

/**
 * 支付、提现账户工具类
 * Created by HaoJiang on 2016/9/26.
 */
public class AccountNumberUtils {

    public static String hideBankCardAccount(String account) {
        return "**** **** **** " + account.substring(account.length() - 4, account.length());
    }

    public static String hideAlipayAccount(String account) {
        boolean isPhoneNumber = !account.contains("@");
        if (isPhoneNumber) {
            //电话号码
            int length = account.length();
            account = account.substring(0, length - 8) + "****" + account.substring(length - 8, length - 4);
        } else {//邮箱号码
            int indexOf = account.indexOf("@");
            String left = account.substring(0, indexOf);
            String right = account.substring(indexOf, account.length());
            if (left.length() >= 3) {
                account = left.substring(0, 3) + "****" + right;
            }
        }
        return account;
    }
}
