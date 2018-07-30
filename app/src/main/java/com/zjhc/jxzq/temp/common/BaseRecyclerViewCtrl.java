package com.zjhc.jxzq.temp.common;

import android.databinding.ObservableField;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zjhc.jxzq.temp.views.SwipeListener;

/**
 * @Author szh
 * @Date 2018/7/6.
 * @Description
 */

public class BaseRecyclerViewCtrl {
    //recyclerView页面布局显示
    public ObservableField<BaseRecyclerViewVM> viewModel = new ObservableField<>();
    public ObservableField<SwipeListener>     listener  = new ObservableField<>();
    /** 下拉刷新控件 */
    private SwipeToLoadLayout swipeLayout;

    public SwipeToLoadLayout getSwipeLayout() {
        return swipeLayout;
    }

    public void setSwipeLayout(SwipeToLoadLayout swipeToLoadLayout) {
        this.swipeLayout = swipeToLoadLayout;
    }
}
