package com.jnn.mylibrary.util;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by jnn on 2018/6/5
 * editText帮助类
 */
public class EditViewUtil {
    private static final String TAG = "EditViewUtil";

    //获取焦点 弹出键盘
    public static void openFocus(EditText et) {
        et.setCursorVisible(true);
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
        et.findFocus();
    }

    //关闭焦点 关闭键盘
    public static void closeFocus(EditText et) {
        et.setCursorVisible(false);
        et.setFocusable(false);
        et.setFocusableInTouchMode(false);
    }

    //默认先关闭焦点，触摸获取焦点
    public static void focusManager(final EditText et) {
        closeFocus(et);
        et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openFocus(et);
                return false;
            }
        });
    }

    /**
     * 过滤特殊字符
     *
     * @param editText
     */
    public static void inputFilerSpecialChars(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~@#$%^&*()+=|{}''\\[\\].<>/@#￥%&*（）——+|{}【】‘；：”“’、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }


    public static void t(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            /**
             * 输入框改变之前触发
             * @param s 输入字符前 输入框的字符
             * @param start 输入字符的开始位置
             * @param count 被替换的字符个数
             * @param after 替换后的字符个数
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeText =>>>" + s.toString() + "start" + start + "count:" + count + "after:" + after);
            }

            /**
             * 输入框改变时触发，从start位置开始before个字符被count个字符替换；
             * 比如删除一个字符就是before=1,count=0
             * @param s 输入框中的所有字符
             * @param start 输入字符的开始位置
             * @param before 被替换的字符个数
             * @param count 替换后的字符个数
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "afterText =>>>" + s.toString() + "start:" + start + "before:" + before + "count:" + count);
            }


            /**
             * 输入框改变后
             * @param editable 输入框最终字符内容 根据需求巧妙利用Editable类方法
             */
            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, "afterText =>>>" + editable.toString());
            }
        });
    }

    /**
     * 当输入框输入到达最大值后 不可继续输入
     *
     * @param editable
     * @param max      输入字符最大值
     */
    public static void max(Editable editable, int max) {
        if (editable.length() > max) {
            editable.delete(max, editable.length());
        }
    }
}
