package com.example.flowlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author timcoder
 * @Date 2019/9/17
 * <p>
 * 流式标签
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
//        setWillNotDraw(false);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setWillNotDraw(false);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setWillNotDraw(false);
    }

    List<Integer> lineHeight = new ArrayList<>();
    List<List<View>> lineViews = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        RuntimeException re = new RuntimeException();
        re.fillInStackTrace();
        Log.e("zangdianbin", "onMeasure", re);

        // TODO onMeasure会调用两次，所以有些计算最好放在onLayout中。

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // TODO 当前控件的宽高(FlowLayout)
        int measureWidth = 0;
        int measureHeight = 0;

        lineViews.clear();
        lineHeight.clear();

        // 当前行宽高
        int iChildWidth = 0;
        int iChildHeight = 0;

        int iCurWidth = 0;
        int iCurHeight = 0;

        List<View> viewList = new ArrayList<>();

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            // 确定两个事情，当前行高，当前行宽
            View child = getChildAt(i);
            // 先让控件测量自己
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 获取当前的LayoutParams
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            // 获取实际宽高
            iChildWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            iChildHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            // 是否需要换行
            if (iCurWidth + iChildWidth > widthSize) {

                // 保存当前行信息
                measureWidth = Math.max(measureWidth, iCurWidth);
                measureHeight += iCurHeight;
                lineHeight.add(iCurHeight);
                lineViews.add(viewList);

                // 更新新的行信息
                iCurWidth = iChildWidth;
                iCurHeight = iChildHeight;

                viewList = new ArrayList<>();
                viewList.add(child);

            } else {
                iCurWidth += iChildWidth;
                iCurHeight = Math.max(iCurHeight, iChildHeight);
                viewList.add(child);
            }

            // 如果正好是最后一行需换行
            if (i == childCount - 1) {
                // 记录当前行的最大宽度，高度累加
                measureWidth = Math.max(measureWidth, iCurWidth);
                measureHeight += iCurHeight;

                // 将当前行的viewList添加至总的mViewsList，将行高添加至总的行高List
                lineViews.add(viewList);
                lineHeight.add(iCurHeight);
            }
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : measureWidth,
                heightMode == MeasureSpec.EXACTLY ? heightSize : measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        RuntimeException re = new RuntimeException();
        re.fillInStackTrace();
        Log.e("zangdianbin", "onLayout", re);

        int left, top, right, bottom;
        int curLeft = 0;
        int curTop = 0;
        for (int i = 0; i < lineViews.size(); i++) {
            List<View> lineView = lineViews.get(i);
            for (int j = 0; j < lineView.size(); j++) {
                View view = lineView.get(j);
                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                left = curLeft + params.leftMargin;
                top = curTop + params.topMargin;
                right = left + view.getMeasuredWidth();
                bottom = top + view.getMeasuredHeight();

                view.layout(left, top, right, bottom);

                curLeft += view.getMeasuredWidth() + params.leftMargin + params.rightMargin;

            }
            curLeft = 0;
            curTop += lineHeight.get(i);
        }
        lineViews.clear();
        lineHeight.clear();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        RuntimeException re = new RuntimeException();
        re.fillInStackTrace();
        Log.e("zangdianbin", "onDraw", re);


        canvas.drawArc(new RectF(0,0,200,200), 0,120, true, new Paint());

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e("zangdianbin", "dispatchDraw");
    }
}
