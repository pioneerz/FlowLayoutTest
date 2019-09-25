package com.example.flowlayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
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

    private int mTranslate = 0;
    private int mRows = 0;
    private Matrix mMatrix;
    private int mLineCount;
    private int mLineHeight;
    private int mScreenWidth;
    private int mTextHeight = 0;
    private int mTextWidth = 0;

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
        mTextRect = new Rect();
        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        String text = getText().toString();
        mTextPaint = getPaint();
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);
        mTextWidth = mTextRect.width();
        mTextHeight = mTextRect.height();
        mScreenWidth = DensityUtils.screenWidth(mContext);
        mLineCount = mTextWidth / mScreenWidth + (mTextWidth % mScreenWidth == 0 ? 0 : 1);
        mLineHeight = mTextRect.height() / mLineCount;

        int needWidth = mTextWidth / text.length() * 3;
        mLinearGradient = new LinearGradient(0, 0, needWidth, mLineHeight,
                new int[]{0x22ffffff, 0xffffffff, 0x22ffffff}, null, Shader.TileMode.CLAMP);
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
        mTranslate += 10;
        if (mRows >= mTextHeight) {
            mRows = 0;
            mTranslate = 0;
        } else {
            if (mTranslate > mScreenWidth) {
                mRows += mLineHeight;
                mTranslate = 0;
            } else if (mTranslate > mTextRect.width()) {
                mTranslate = 0;
                mRows = 0;
            }
        }
        mMatrix.reset();
        mMatrix.setTranslate(mTranslate, mRows);
        mLinearGradient.setLocalMatrix(mMatrix);
        postInvalidateDelayed(200);
    }
}
