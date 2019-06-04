package com.example.jnn.today.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.jnn.today.R;

/**
 * 自定义view练习
 */
public class OneView extends View {
    public static final String TAG = OneView.class.getSimpleName();

    @Override
    protected void onDraw(Canvas canvas) {
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);

        //简单例子 画一个圆
        int r = getMeasuredHeight() / 2;
        float centerX = r + getX();
        float centerY = r + getTop();

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        canvas.drawCircle(centerX, centerY, r, paint);
    }


    /**
     * 当该View需要指定宽高 或 更改宽高 时，重写该方法
     * 比如，不管布局文件中怎么设置，希望该view都是圆形
     *
     * @param widthMeasureSpec 尺寸+模式
     *                         该方法会执行两遍,根据布局中设置的width，height 返回模式和尺寸
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = getSize(100, widthMeasureSpec);
        int heightSize = getSize(100, heightMeasureSpec);
        Log.e(TAG, "widthSize:" + widthSize + "\theightSize:" + heightSize);
        if (widthSize < heightSize) {
            heightSize = widthSize;
        } else {
            widthSize = heightSize;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    private int getSize(int defSize, int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int resultSize = defSize;

        switch (mode) {
            case MeasureSpec.EXACTLY:
                //固定大小，布局中设置了具体值「match/200dp」，我们可以不用处理
                Log.e(TAG, "EXACTLY：" + "size:" + size);
                resultSize = size;
                break;
            case MeasureSpec.AT_MOST:
                //尽可能大的尺寸，布局中设置了 wrap
                Log.e(TAG, "AT_MOST：" + "size:" + size);
                resultSize = Math.max(defSize, size);
                break;
            case MeasureSpec.UNSPECIFIED:
                //未指定大小，比如什么情况呢？
                Log.e(TAG, "UNSPECIFIED：" + "size:" + size);
                break;
            default:
                break;
        }
        return resultSize;
    }


    /**
     * 构造1
     * 系统默认调用：java代码中调用
     */
    public OneView(Context context) {
        super(context);
    }

    /**
     * 构造2
     * 系统默认调用：xml文件声明时调用
     *
     * @param context
     * @param attrs   自定义属性集合 R.styleable.xx
     */
    public OneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性集合
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OneView);
        //获取单个属性值
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.OneView_defSize, 100);

        //用完记得释放
        typedArray.recycle();
    }

    /**
     * 构造3
     * 系统不调用，需要手动调用该构造（比如在 构造2 中，super方法注释掉，手动 this(,,,) 构造3）
     *
     * @param defStyleAttr 默认属性值 R.attrs.xx，设置相对繁琐，一般不用
     *                     如果没有在 layout xml和style 中指定属性值，就用这个值，所以是默认值，
     *                     但这个attribute要在Theme中指定，且是指向一个Style的引用，
     *                     如果这个参数传入0表示不向Theme中搜索默认值
     */
    public OneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 构造4
     * 系统不调用，需要手动调用该构造
     *
     * @param defStyleRes 这个也是指向一个Style的资源ID，
     *                    但是仅在 defStyleAttr为0 或 defStyleAttr不为0但Theme中没有为defStyleAttr属性赋值时起作用
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public OneView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
