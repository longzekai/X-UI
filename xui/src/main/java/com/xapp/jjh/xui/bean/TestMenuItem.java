package com.xapp.jjh.xui.bean;

import android.graphics.Color;

/**
 * Created by Taurus on 2016/8/23.
 */
public class TestMenuItem extends BaseMenuItem{
    public TestMenuItem() {
    }

    public TestMenuItem(int iconId, String itemText) {
        super(iconId, itemText);
    }

    @Override
    public int getItemTextColor() {
        return Color.BLUE;
    }
}
