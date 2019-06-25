package com.zjhc.jxzq.jxzq.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.zjhc.jxzq.jxzq.MyApplication;

/**
 * @Author szh
 * @Date 2019/4/12
 * @Description
 */
public class ResourceUtil {
    public static int  getColor(int resource){
        return getColor(MyApplication.getActivity(),resource);
    }
    public static int getColor(Context context,int resource){
        return context.getResources().getColor(resource);
    }
    public static Drawable getDrawable(int resource){
        return getDrawable(MyApplication.getActivity(),resource);
    }
    public static Drawable getDrawable(Context context,int resource){
        return context.getResources().getDrawable(resource);
    }
    public static String  getString(int resource){
        return getString(MyApplication.getActivity(),resource);
    }
    public static String getString(Context context,int resource){
        return context.getResources().getString(resource);
    }
}
