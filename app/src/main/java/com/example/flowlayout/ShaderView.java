package com.example.flowlayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author Administrator
 * 2019/9/24 0024.
 */
public class ShaderView extends View {

    private Context mContext;
    private Paint mShaderPaint;
    private Bitmap mBitmap;
    private BitmapShader mShader;

    public ShaderView(Context context) {
        super(context);
        initPaint(context);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        mContext = context;
        mShaderPaint = new Paint();
        mShaderPaint.setAntiAlias(true);
        mShaderPaint.setStyle(Paint.Style.FILL);

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.download);
        mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mShaderPaint.setShader(mShader);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(DensityUtils.screenWidth(mContext), 400);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, 200, 100, mShaderPaint);
        canvas.save();
        canvas.translate(0, 140);
        canvas.drawCircle(80, 80, 80, mShaderPaint);
        canvas.restore();
        canvas.drawRoundRect(10, 310, 200, 400, 20, 20, mShaderPaint);

    }
}
