package com.zjhc.jxzq.temp.common;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zjhc.jxzq.temp.R;

/**
 * @Author szh
 * @Date 2018/7/5.
 * @Description
 */

public class BaseActivity extends AppCompatActivity {
    private TextView title;
    private ImageView leftBtn;
    private ImageView rightBtn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            // 设置一个状态栏颜色
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
        //初始化@Autowired注解的字段
        ARouter.getInstance().inject(this);

    }

    public void setTitle(int id){
        if(title == null){
            title = findViewById(R.id.title);
        }
        title.setText(getResources().getString(id));
    }
   public void setLeftBtn(){
        if(leftBtn == null){
            leftBtn = findViewById(R.id.leftBtn);
        }
       leftBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
   }
   public void hideLeftBtn(){
       if(leftBtn == null){
           leftBtn = findViewById(R.id.leftBtn);
       }
       leftBtn.setVisibility(View.GONE);
   }
}
