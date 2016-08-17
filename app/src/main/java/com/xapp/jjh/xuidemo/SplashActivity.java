package com.xapp.jjh.xuidemo;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xapp.jjh.xui.activity.ToolsActivity;
import com.xapp.jjh.xuidemo.utils.AddressUtils;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/15.
 * ------------------------------------
 */
public class SplashActivity extends ToolsActivity {
    @Override
    public View getContentView(LayoutInflater layoutInflater, ViewGroup container) {
        return inflate(R.layout.activity_splash);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void initData() {
        fullScreen();
        new Thread(){
            @Override
            public void run() {
                AddressUtils.init(getApplicationContext());
            }
        }.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                backSpace();
            }
        }, 2000);
    }
}
