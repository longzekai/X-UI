package com.xapp.jjh.xuidemo.bean;

import android.graphics.Color;

import com.xapp.jjh.xui.bean.BaseMenuItem;

/**
 * Created by Taurus on 2016/8/23.
 */
public class TestMenuItem extends BaseMenuItem {
    public TestMenuItem() {
    }

    public TestMenuItem(int iconId, String itemText) {
        super(iconId, itemText);
    }

    @Override
    public int getItemTextColor() {
        return Color.parseColor("#666666");
    }

    @Override
    public int getItemPartLineColor() {
        return Color.parseColor("#22000000");
    }
}
