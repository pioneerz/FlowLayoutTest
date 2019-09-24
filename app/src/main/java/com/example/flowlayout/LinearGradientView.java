package com.example.flowlayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @author Administrator
 * 2019/9/24 0024.
 */
@SuppressLint("AppCompatCustomView")
public class LinearGradientView extends TextView {

    private Context mContext;
    private Paint mTextPaint;

    private LinearGradient mLinearGradient;

    private Rect mTextRect;

    public LinearGradientView(Context context) {
        super(context);
        initPaint(context);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        mContext = context;
//        mTextPaint = new Paint();
//        mTextPaint.setAntiAlias(true);
//        mTextPaint.setTextSize(60);
//        mTextPaint.setStyle(Paint.Style.FILL);
//        mTextPaint.setColor(Color.GRAY);

        mTextRect = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        String text = getText().toString();
        mTextPaint = getPaint();
        Log.e("zangdianbin", "onSizeChanged=" + text);
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);
        int width3 = mTextRect.width() / text.length() * 3;
        mLinearGradient = new LinearGradient(0, 80, width3, mTextRect.height(),
                new int[]{0xeaeaea, 0xfffff, 0xeaeaea}, null, Shader.TileMode.CLAMP);
        mTextPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(DensityUtils.screenWidth(mContext), 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
