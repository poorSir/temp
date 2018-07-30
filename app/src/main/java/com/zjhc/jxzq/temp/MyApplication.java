package com.zjhc.jxzq.temp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zjhc.jxzq.temp.common.AppConfig;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author szh
 * @Date 2018/7/5.
 * @Description
 */

public class MyApplication extends Application{
    private static Activity mActivity;
    private static Context mContext;
    private static List<Activity> activityList = new ArrayList<>();
    public void onCreate(){
        super.onCreate();
        mContext = getApplicationContext();
        initRouter();
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                if(mActivity == null){
                    mActivity = activity;
                }else{
                    if(mActivity != activity){
                        mActivity = activity;
                    }else{

                    }
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                removeActivity(activity);
            }
        });
    }
    //添加actiivty
    public void addActivity(Activity actiivty){
        if(!activityList.contains(actiivty)){
            activityList.add(actiivty);
        }
    }
    //销毁单个Activity
    public void removeActivity(Activity activity){
        if(activityList.contains(activity)){
            activityList.remove(activity);
        }
    }
    //销毁所有的activity
    public static  void removeAllActivity(){
        for(Activity activity :activityList){
            activity.finish();
        }
    }
    /**
     * 路由初始化
     */
    private void initRouter() {
        if (AppConfig.isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    public static Activity getActivity(){
        return mActivity;
    }
    public static Context getContext(){
        return mContext;
    }

    public static  List<Activity> getActivityList(){
        return activityList;
    }
}
