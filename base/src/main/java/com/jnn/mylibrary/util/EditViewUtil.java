package com.jnn.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by jnn on 2018/6/5
 * editText帮助类
 */
public class EditViewUtil {
    private static final String TAG = "EditViewUtil";

    /**
     * 获取焦点
     */
    public static void openFocus(EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        //必须加这句才能重新获取焦点
        et.requestFocus();
    }

    /**
     * 关闭焦点
     */
    public static void closeFocus(EditText et) {
        et.setFocusable(false);
        et.setFocusableInTouchMode(false);
    }

    /**
     * 获取焦点 弹出键盘
     * 需求场景：搜索页面，希望一进页面，就聚焦搜索框，用户可直接输入
     */
    public static void openFocus(final Activity activity, final EditText et) {
        //获取焦点
        openFocus(et);
        //弹出软键盘「延迟显示，否则弹不出」
        et.postDelayed(new Runnable() {
            @Override
            public void run() {
                showInput(activity, et);
            }
        }, 1000);
    }

    /**
     * 先关闭焦点，触摸获取焦点
     * 需求场景：进入页面不希望输入框有焦点
     */
    public static void focusManager(final EditText et) {
        //先关闭焦点
        closeFocus(et);
        et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //触摸获取焦点，软键盘自动弹出
                openFocus(et);
                return false;
            }
        });
    }


    /**
     * 显示键盘
     */
    public static void showInput(Activity activity, final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(et, 0);
        }
    }

    /**
     * 隐藏键盘
     */
    public static void hideInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = activity.getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 触摸软键盘外部，收起键盘
     * 需求场景：EditText的优化体验
     * 使用：在xml根布局中添加两个属性，抢夺EditText焦点
     * android:clickable="true"
     * android:focusableInTouchMode="true"
     */
    public static void touchHideInput(final Activity activity, EditText et) {
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideInput(activity);
                }
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
                if (matcher.find()) {
                    return "";
                } else {
                    return null;
                }
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
