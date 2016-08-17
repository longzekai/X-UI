package com.xapp.jjh.xuidemo;

import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xapp.jjh.xui.activity.LoadStateActivity;
import com.xapp.jjh.xui.inter.PageState;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/16.
 * ------------------------------------
 */
public class FourthActivity extends LoadStateActivity {

    @Override
    public View getErrorView() {
        return inflate(R.layout.layout_error_state);
    }

    @Override
    public View getLoadingView() {
        return inflate(R.layout.layout_loading);
    }

    @Override
    public View getContentView(LayoutInflater layoutInflater, ViewGroup container) {
        return inflate(R.layout.activity_fourth);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void initData() {
        setStatusLightMode(true);
        setStatusBarColor(Color.WHITE);
        setPageState(PageState.LOADING);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setPageState(PageState.ERROR);
            }
        },2000);
    }

    @Override
    public void retryLoad() {
        super.retryLoad();
        setPageState(PageState.LOADING);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setPageState(PageState.SUCCESS);
            }
        },2000);
    }
}
