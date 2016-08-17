package com.xapp.jjh.xuidemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xapp.jjh.xui.activity.TopBarActivity;
import com.xapp.jjh.xui.inter.MenuType;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/15.
 * ------------------------------------
 */
public class ThirdActivity extends TopBarActivity {

    private TextView tv_full_screen;
    private TextView tv_quit_screen;
    private TextView tv_hidden_top_bar;
    private TextView tv_show_top_bar;
    private TextView tv_change_top_bar_color;
    private TextView tv_change_status_bar_color;
    private TextView tv_set_navigation_icon;
    private TextView tv_set_menu_icon;
    private TextView tv_set_menu_text;
    private TextView tv_show_single_button_dialog;

    @Override
    public View getContentView(LayoutInflater layoutInflater, ViewGroup container) {
        return inflate(R.layout.activity_third);
    }

    @Override
    public void findViewById() {
        tv_full_screen = findView(R.id.tv_full_screen);
        tv_quit_screen = findView(R.id.tv_quit_screen);
        tv_hidden_top_bar = findView(R.id.tv_hidden_top_bar);
        tv_show_top_bar = findView(R.id.tv_show_top_bar);
        tv_change_top_bar_color = findView(R.id.tv_change_top_bar_color);
        tv_change_status_bar_color = findView(R.id.tv_change_status_bar_color);
        tv_set_navigation_icon = findView(R.id.tv_set_navigation_icon);
        tv_set_menu_icon = findView(R.id.tv_set_menu_icon);
        tv_set_menu_text = findView(R.id.tv_set_menu_text);
        tv_show_single_button_dialog = findView(R.id.tv_show_single_button_dialog);
    }

    @Override
    public void initData() {
        setTopBarTitle("THIRD");
        showLoadingDialog("Loading...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeLoadingDialog();
            }
        }, 1000);
    }

    @Override
    public void setListener() {
        super.setListener();
        tv_full_screen.setOnClickListener(this);
        tv_quit_screen.setOnClickListener(this);
        tv_hidden_top_bar.setOnClickListener(this);
        tv_show_top_bar.setOnClickListener(this);
        tv_change_top_bar_color.setOnClickListener(this);
        tv_change_status_bar_color.setOnClickListener(this);
        tv_set_navigation_icon.setOnClickListener(this);
        tv_set_menu_icon.setOnClickListener(this);
        tv_set_menu_text.setOnClickListener(this);
        tv_show_single_button_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_full_screen:
                fullScreen();
                break;

            case R.id.tv_quit_screen:
                quitFullScreen();
                break;

            case R.id.tv_hidden_top_bar:
                setTopBarVisible(false);
                break;

            case R.id.tv_show_top_bar:
                setTopBarVisible(true);
                break;

            case R.id.tv_change_top_bar_color:
                setTopBarColor(Color.parseColor("#5566aa"));
                break;

            case R.id.tv_change_status_bar_color:
                setStatusBarColor(Color.parseColor("#475aa4"));
                break;

            case R.id.tv_set_navigation_icon:
                setNavigationIcon(R.mipmap.icon_close);
                break;

            case R.id.tv_set_menu_icon:
                setMenuType(MenuType.ICON,R.mipmap.icon_test_menu);
                break;

            case R.id.tv_set_menu_text:
                setMenuType(MenuType.TEXT,R.string.text_menu);
                break;

            case R.id.tv_show_single_button_dialog:
                showDialogSingleButton("single button dialog","OK",null);
                break;
        }
    }

    @Override
    public void onMenuClick() {
        super.onMenuClick();
        Intent intent = new Intent(getApplicationContext(),CustomTopBarActivity.class);
        startActivity(intent);
    }
}
