package com.zjhc.jxzq.temp.common;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import com.zjhc.jxzq.temp.views.DividerLine;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * @Author szh
 * @Date 2018/7/6.
 * @Description recycleview布局绑定
 */

public abstract class BaseRecyclerViewVM<T> extends BaseObservable{
    //数据源
    public ObservableList<T> items = new ObservableArrayList<>();
    public abstract void setItemView(ItemBinding itemBinding ,int position ,T item);
    //界面绑定
    public final OnItemBind<T> onItemBind  =new OnItemBind<T>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, T item) {
            setItemView(itemBinding,position,item);
        }
    };
    /**
     * 分割线
     * 水平方向 -0
     * 垂直方向 -1
     * 全方向 - 9
     */
    public       int     type  = DividerLine.HORIZONTAL;
    /**事件监听*/
    public interface OnEventListener<T>{
        void onClickListener(T item);
    }
    public OnEventListener onEventListener;
    public OnEventListener getOnEvnetListener() {
        return onEventListener;
    }

    public void setOnEventListener(OnEventListener onEventListener) {
        this.onEventListener = onEventListener;
    }
}
