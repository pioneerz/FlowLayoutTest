package com.example.flowlayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * @Author timcoder
 * @Date 2019-09-19
 */
public class CircleImageView extends View {

    private Context mContext;

    private Paint mPaint;
    private Path mLinePath;

    private Paint mCirclePaint;
    private Path mPath;

    private Paint mQuadPaint;
    private Path mQuadPath;

    private Paint mCubicPaint;
    private Path mCubicPath;

    private Paint mBitmapPaint;
    private Paint mBitmapColorFilterPaint;
    private Bitmap mBitmap;

    private Paint mTextPaint;
    private String mTextStr = "TimCoder";
    private Rect mRect;
    private int mWidth;

    private Paint mShaderPaint;
    private Shader mShader;

    public CircleImageView(Context context) {
        super(context);
        initPaint(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {

        mContext = context;

        initLine();

        initCircle();

        initQuad();

        initCubic();

        initBitmap();

        initText();

        initShader();
    }

    private void initShader() {
        mShaderPaint = new Paint();
        mShaderPaint.setAntiAlias(true);
        mShaderPaint.setStyle(Paint.Style.FILL);

//        mShader = new LinearGradient(100, 720, 500, 720, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        mShader = new LinearGradient(200, 720, 400, 920,
                new int[]{Color.RED, Color.GREEN, Color.BLUE},
                null, Shader.TileMode.REPEAT);
        mShaderPaint.setShader(mShader);
    }

    private void initText() {
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(90);
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setShadowLayer(10, 0, 0, Color.RED);

        mRect = new Rect();
    }

    private void initLine() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        // TODO 设置线冒
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // TODO 设置线段连接处样式,设置拐角的形状
        // TODO MITER 锐角:当角度过小，所需的延长线太长时，自动转为BEVEL
        // TODO BEVEL 平角
        // TODO ROUND 圆角:
        mPaint.setStrokeJoin(Paint.Join.MITER);
        // TODO miter对于转角长度的限制:指尖角的外缘端点到内部拐角的距离与线条宽度的比
        mPaint.setStrokeMiter(10);
        mPaint.setStrokeWidth(30);

        // 设置图形的轮廓
        // 将拐角变成圆角
        CornerPathEffect cornerPathEffect = new CornerPathEffect(20);
        DiscretePathEffect discretePathEffect = new DiscretePathEffect(10, 5);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        Path path = new Path();
        path.addCircle(10, 10, 10, Path.Direction.CW);
        PathDashPathEffect pathDashPathEffect = new PathDashPathEffect(path, 30, 0, PathDashPathEffect.Style.TRANSLATE);
        SumPathEffect sumPathEffect = new SumPathEffect(dashPathEffect, discretePathEffect);
        ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect, discretePathEffect);

//        mPaint.setPathEffect(new CornerPathEffect(20));
//        mPaint.setPathEffect(new DiscretePathEffect(10, 5));
//        mPaint.setPathEffect(new DashPathEffect(new float[]{20, 10, 5, 10}, 0));

        mPaint.setPathEffect(pathDashPathEffect);

        mLinePath = new Path();
        mLinePath.moveTo(300, 50);
        mLinePath.lineTo(600, 50);
        mLinePath.lineTo(400, 150);
    }

    private void initBitmap() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        mBitmapColorFilterPaint = new Paint();
        mBitmapColorFilterPaint.setAntiAlias(true);
        LightingColorFilter lightColorFilter = new LightingColorFilter(0x00ffff, 0x0);
        mBitmapColorFilterPaint.setColorFilter(lightColorFilter);
        mBitmapColorFilterPaint.setStyle(Paint.Style.STROKE);
//        mBitmapColorFilterPaint.setShadowLayer(20, 0, 0, Color.RED);
        // 降低色彩深度时的优化dither
        mBitmapColorFilterPaint.setDither(true);
        // 双线性过滤优化图片放大时的效果
        mBitmapColorFilterPaint.setFilterBitmap(true);

        mBitmap = ((BitmapDrawable) ContextCompat.getDrawable(mContext, R.mipmap.download)).getBitmap();
    }

    private void initCubic() {
        mCubicPaint = new Paint();
        mCubicPaint.setColor(Color.RED);
        mCubicPaint.setStrokeWidth(3);
        mCubicPaint.setAntiAlias(true);
        mCubicPaint.setStyle(Paint.Style.STROKE);

        mCubicPath = new Path();
        mCubicPath.moveTo(160, 300);
        mCubicPath.cubicTo(240, 400, 300, 200, 400, 300);

    }

    private void initQuad() {
        mQuadPaint = new Paint();
        mQuadPaint.setColor(Color.RED);
        mQuadPaint.setStrokeWidth(3);
        mQuadPaint.setAntiAlias(true);
        mQuadPaint.setStyle(Paint.Style.STROKE);
//        mQuadPaint.setPathEffect(new CornerPathEffect(20));

        mQuadPath = new Path();
        mQuadPath.moveTo(20, 300);
        mQuadPath.quadTo(70, 200, 120, 300);
        mQuadPath.moveTo(20, 300);
        mQuadPath.quadTo(70, 400, 120, 300);
    }

    private void initCircle() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStrokeWidth(3);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        // 设置填充方式
        mPath.setFillType(Path.FillType.WINDING);
        mPath.addCircle(100, 100, 80, Path.Direction.CW);
        mPath.addCircle(180, 100, 80, Path.Direction.CCW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTextPaint.getTextBounds(mTextStr, 0, mTextStr.length(), mRect);
        int screenWidth = DensityUtils.screenWidth(mContext);
        mWidth = screenWidth / 2 - mRect.width() / 2;

        // TODO 将兹定于view添加到scrollview中时，scrollview初始化的高度为0，所以不能显示自定义view，所以我们需要自己设定高度.
        setMeasuredDimension(DensityUtils.screenWidth(mContext), 950);
    }

    /**
     * performTraversals->performDraw->draw->受硬件加速影响(mAttachInfo.mThreadedRenderer.draw或者drawSoftware)
     * 如果开了硬件加速，就会调用mThreadRender的draw方法
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mLinePath, mPaint);
//        canvas.drawLine(300, 50, 600, 50, mPaint);
//        canvas.drawLine(600, 50, 400, 150, mPaint);

        canvas.drawPath(mPath, mCirclePaint);

        canvas.drawPath(mQuadPath, mQuadPaint);

        canvas.drawPath(mCubicPath, mCubicPaint);

        canvas.drawBitmap(mBitmap, 10, 410, mBitmapPaint);

        canvas.drawBitmap(mBitmap, 190, 410, mBitmapColorFilterPaint);

        mBitmapColorFilterPaint.setColorFilter(new LightingColorFilter(0xff00ff, 0x0));
        canvas.drawBitmap(mBitmap, 370, 410, mBitmapColorFilterPaint);

        mBitmapColorFilterPaint.setColorFilter(new LightingColorFilter(0xffff00, 0x0));
        canvas.drawBitmap(mBitmap, 550, 410, mBitmapColorFilterPaint);

//        mBitmapColorFilterPaint.setColorFilter(new PorterDuffColorFilter(0x00ffff, PorterDuff.Mode.SRC_OUT));
//        canvas.drawBitmap(mBitmap, 10, 580, mBitmapColorFilterPaint);

        canvas.drawText(mTextStr, mWidth, 650, mTextPaint);

        canvas.drawRoundRect(100, 720, 500, 920, 20, 20, mShaderPaint);

    }
}
