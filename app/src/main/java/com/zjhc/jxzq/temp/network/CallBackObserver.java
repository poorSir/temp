package com.zjhc.jxzq.temp.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.zjhc.jxzq.temp.MyApplication;
import com.zjhc.jxzq.temp.R;
import com.zjhc.jxzq.temp.common.BaseParam;
import com.zjhc.jxzq.temp.common.Constant;
import com.zjhc.jxzq.temp.utils.SpUtil;
import com.zjhc.jxzq.temp.utils.TextUtil;
import com.zjhc.jxzq.temp.utils.ToastUtil;

import java.io.IOException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author szh
 * @Date 2018/7/6.
 * @Description 请求回调
 */

public abstract class CallBackObserver<T> implements Observer<T> {
    private Disposable d;
    //data数据格式是否是数组
    private boolean isList;

    public CallBackObserver(boolean isList) {
        this.isList = isList;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(T t) {
        if (isList) {
            HttpListResult httpResult = (HttpListResult) t;
            if (Constant.String_2_.equals(httpResult.getCode())) {
                Activity activity = MyApplication.getActivity();
                AlertDialog alertDialog = new AlertDialog.Builder(activity)
                        .setMessage("用户信息过期，请重新登录")//内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                if(MyApplication.getActivityList().contains(activity)){
                    alertDialog.show();
                }
                return;
            }
            if (Constant.String_0.equals(httpResult.getCode())) {
                onSuccess(t);
            } else {
                if (TextUtil.isEmpty(httpResult.getMsg())) {
                    ToastUtil.show(R.string.error_unknow);
                } else {
                    ToastUtil.show(httpResult.getMsg());
                }
                onFailure(t);
            }
        } else {
            HttpResult httpResult = (HttpResult) t;
            if (Constant.String_2_.equals(httpResult.getCode()))
            {
                Activity activity = MyApplication.getActivity();
                AlertDialog alertDialog = new AlertDialog.Builder(activity)
                        .setMessage("用户信息过期，请重新登录")//内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                if(MyApplication.getActivityList().contains(activity)){
                    alertDialog.show();
                }
                return;
            }
            if (Constant.String_0.equals(httpResult.getCode())) {
                onSuccess(t);
            } else {
                if (TextUtil.isEmpty(httpResult.getMsg())) {
                    ToastUtil.show(R.string.error_unknow);
                } else {
                    ToastUtil.show(httpResult.getMsg());
                }
                onFailure(t);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        d.dispose();
        if (e instanceof HttpException) {
            ToastUtil.show(((HttpException) e).code() + "");
        }
        if (e instanceof IOException) {
            ToastUtil.show(R.string.error_socket_timeout);
        }
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        d.dispose();
    }

    public abstract void onSuccess(T response);

    public abstract void onFailure(T response);
}
