package com.linz.lib_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by 牛栋 on 2018/5/13.
 * 需求场景：
 * 1.使用TextView设置图片时，需求设置图片大小;
 * 2.TextView设置宽高，使图片和文字居中。
 */
public class DrawableTextView extends AppCompatTextView {
    public static final String TAG = DrawableTextView.class.getSimpleName();

    public DrawableTextView(Context context) {
        super(context);
        init(null);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DrawableTextView);
            int width = a.getDimensionPixelSize(R.styleable.DrawableTextView_drawableWidth, 0);
            int height = a.getDimensionPixelSize(R.styleable.DrawableTextView_drawableHeight, 0);

            Drawable left = a.getDrawable(R.styleable.DrawableTextView_android_drawableLeft);
            Drawable top = a.getDrawable(R.styleable.DrawableTextView_android_drawableTop);
            Drawable right = a.getDrawable(R.styleable.DrawableTextView_android_drawableRight);
            Drawable bottom = a.getDrawable(R.styleable.DrawableTextView_android_drawableBottom);

            if (null != left && width > 0 && height > 0) {
                left.setBounds(0, 0, width, height);
            }
            if (null != top && width > 0 && height > 0) {
                top.setBounds(0, 0, width, height);
            }
            if (null != right && width > 0 && height > 0) {
                right.setBounds(0, 0, width, height);
            }
            if (null != bottom && width > 0 && height > 0) {
                bottom.setBounds(0, 0, width, height);
            }
            setCompoundDrawables(left, top, right, bottom);
            a.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        Drawable topDrawable = drawables[1];
        Drawable rightDrawable = drawables[2];
        Drawable bottomDrawable = drawables[3];

        //水平方向（left right）
        Drawable horiDrawable = null;
        //垂直方向（top bottom）
        Drawable verDrawable = null;
        if (leftDrawable != null) {
            horiDrawable = leftDrawable;
        }
        if (rightDrawable != null) {
            horiDrawable = rightDrawable;
        }
        if (topDrawable != null) {
            verDrawable = topDrawable;
        }
        if (bottomDrawable != null) {
            verDrawable = bottomDrawable;
        }

        //获取文字的宽高
        Rect rect = new Rect();
        String textStr = getText().toString().trim();
        getPaint().getTextBounds(textStr, 0, textStr.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();

        if (horiDrawable != null) {
            //xml设置center_vertical 生效
            int drawableWidth = horiDrawable.getIntrinsicWidth();
            int drawablePadding = getCompoundDrawablePadding();
            int bodyWidth = drawableWidth + drawablePadding + textWidth;
            canvas.save();
            int width = getWidth() - getPaddingLeft() - getPaddingRight();
            if (width > bodyWidth) {
                canvas.translate((width - bodyWidth) / 2, 0);
            }
        } else if (verDrawable != null) {
            //xml设置center_horizontal 生效
            //获取图片的 高度
            int drawableHeight = verDrawable.getIntrinsicHeight();
            //获取图片和文字的间距
            int drawablePadding = getCompoundDrawablePadding();
            //得到总高度 文字+间距+图片
            int bodyHeight = drawableHeight + drawablePadding + textHeight;
            canvas.save();
            int height = getHeight() - getPaddingTop() - getPaddingBottom();
            if (height > bodyHeight) {
                //只有较大才偏移, 不然会缺部分图片
                canvas.translate(0, (height - bodyHeight) / 2);
            }
        }
        super.onDraw(canvas);
    }
}