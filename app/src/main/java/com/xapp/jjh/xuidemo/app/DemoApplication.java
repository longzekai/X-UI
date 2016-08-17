package com.xapp.jjh.xuidemo.app;


import android.content.Intent;
import android.os.Bundle;

import com.xapp.jjh.xui.application.FilterParams;
import com.xapp.jjh.xui.application.XUIApplication;
import com.xapp.jjh.xui.config.XUIConfig;
import com.xapp.jjh.xuidemo.R;
import com.xapp.jjh.xuidemo.utils.MemroyLeakManager;
import com.xapp.jjh.xuidemo.utils.State;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/15.
 * ------------------------------------
 */
public class DemoApplication extends XUIApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MemroyLeakManager.getInstance().initLeakCanary(State.DEBUG,this);
        XUIConfig.setXUIRedStyle();
//        XUIConfig.setUseSwipeBack(false);
//        XUIConfig.setTopBarHeight(R.dimen.topBarHeight);
//        XUIConfig.setTopBarNavigationIcon(com.xapp.jjh.xui.R.mipmap.back_icon);
//        XUIConfig.setTopBarBgColor(R.color.top_bar_bg);
//        XUIConfig.setStatusBarColor(R.color.status_bar_bg);
//        XUIConfig.setTopBarTitleTextSize(15);
//        XUIConfig.setTopBarMenuTextColor(R.color.top_bar_title_color);
//        XUIConfig.setTopBarTitleTextColor(R.color.top_bar_title_color);
//        XUIConfig.setTopBarMenuTextSize(12);
    }

    @Override
    public FilterParams onStartActivityForResult(Intent intent, int requestCode, Bundle options) {
        return super.onStartActivityForResult(intent, requestCode, options);
    }
}
