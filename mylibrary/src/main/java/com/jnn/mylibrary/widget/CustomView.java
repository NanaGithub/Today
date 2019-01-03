package com.jnn.mylibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.jnn.mylibrary.R;

/**
 *
 */
public class CustomView extends LinearLayout {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        int leftIcon = attributes.getDimensionPixelSize(R.styleable.CustomView_leftIcon, 0);
        int leftIconHeight = attributes.getDimensionPixelSize(R.styleable.CustomView_leftIconHeight, 0);
        int leftIconWidth = attributes.getDimensionPixelSize(R.styleable.CustomView_leftIconWidth, 0);
        //右侧图片
        int rightIcon = attributes.getDimensionPixelSize(R.styleable.CustomView_rightIcon, 0);
        int rightIconHeight = attributes.getDimensionPixelSize(R.styleable.CustomView_rightIconHeight, 0);
        int rightIconWidth = attributes.getDimensionPixelSize(R.styleable.CustomView_rightIconWidth, 0);
        //右侧文字
        String rightText = attributes.getString(R.styleable.CustomView_rightText);
        int rightTextSize = attributes.getDimensionPixelSize(R.styleable.CustomView_rightTextSize, 0);
        int rightTextColor = attributes.getDimensionPixelSize(R.styleable.CustomView_rightTextColor, 0);
    }
}
