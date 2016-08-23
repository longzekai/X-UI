package com.xapp.jjh.xuidemo;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xapp.jjh.xui.activity.TopBarActivity;
import com.xapp.jjh.xui.application.XUIApplication;
import com.xapp.jjh.xui.inter.MenuType;
import com.xapp.jjh.xui.inter.PageState;
import com.xapp.jjh.xuidemo.app.DemoApplication;

/**
 * Created by Taurus on 16/8/14.
 */
public class SecondActivity extends TopBarActivity {

    private TextView tv_tip;

    @Override
    public View getContentView(LayoutInflater layoutInflater, ViewGroup container) {
        return inflate(R.layout.activity_second);
    }

    @Override
    public void findViewById() {
        tv_tip = findView(R.id.tv_tip);
    }

    @Override
    public void initData() {
        setTopBarTitle("SECONDXXXXXXXXXXXXXXXXXXXXX");
        setMenuType(MenuType.TEXT,R.string.test_menu);
        setPageState(PageState.LOADING);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setPageState(PageState.ERROR);
            }
        }, 2000);

        DemoApplication.getInstance().setForegroundBackgroundListener(new XUIApplication.ForegroundBackgroundListener() {
            @Override
            public void enterForeground() {
                tv_tip.setText("come back from launcher !");
            }

            @Override
            public void enterBackground() {

            }
        });
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
        }, 1000);
    }

    @Override
    public void onMenuClick() {
        super.onMenuClick();
        showToast("MENU");
        Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
        startActivity(intent);
    }
}
