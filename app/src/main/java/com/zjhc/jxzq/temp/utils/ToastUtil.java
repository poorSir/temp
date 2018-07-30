package com.zjhc.jxzq.temp.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.zjhc.jxzq.temp.MyApplication;


/**
 * @Author szh
 * @Date 2018/7/6.
 * @Description
 */

public class ToastUtil {
    public static void show(String msg){
        show(MyApplication.getActivity(),msg);
    }
    public static void show(Context context, String msg){
        Toast toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        //显示在中间
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /***
     * 显示string 中的文字
     * @param id
     */
    public static void show(int id) {
        show(MyApplication.getActivity(), MyApplication.getActivity().getString(id));
    }
}
