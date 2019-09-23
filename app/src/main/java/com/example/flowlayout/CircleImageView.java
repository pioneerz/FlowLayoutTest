package com.example.flowlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author timcoder
 * @Date 2019-09-19
 */
public class CircleImageView extends View {

    private Paint mPaint;

    public CircleImageView(Context context) {
        super(context);
        initPaint();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        // TODO 设置线冒
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // TODO 设置线段连接处样式
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(30);
    }

    /**
     * performTraversals->performDraw->draw->受硬件加速影响(mAttachInfo.mThreadedRenderer.draw或者drawSoftware)
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(50, 50, 500, 50, mPaint);
        canvas.drawLine(500, 50, 225, 150, mPaint);
    }
}
