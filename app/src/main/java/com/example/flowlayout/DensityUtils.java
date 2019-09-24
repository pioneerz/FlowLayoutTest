package com.example.flowlayout;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by ymh on 2016/5/10.
 */
public class DensityUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int screenWidthReal(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels > displayMetrics.widthPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
    }

    public static int screenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int screenWidthLess(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels > displayMetrics.widthPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
    }

    public static int statusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }

        return 0;
    }
}
