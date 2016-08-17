package com.xapp.jjh.xuidemo;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xapp.jjh.xui.activity.TopBarActivity;
import com.xapp.jjh.xuidemo.adapter.CardPagerAdapter;
import com.xapp.jjh.xuidemo.bean.CardData;
import com.xapp.jjh.xuidemo.fragment.CardFragment;
import com.xapp.jjh.xuidemo.utils.AnimatorUtils;
import com.xapp.jjh.xuidemo.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/17.
 * ------------------------------------
 */
public class CustomTopBarActivity extends TopBarActivity {

    private ViewPager mPager;
    private View root;
    private TextView tv_custom_title;

    @Override
    public View getCustomTopBarView() {
        return inflate(R.layout.layout_custom_top_bar);
    }

    @Override
    public View getContentView(LayoutInflater layoutInflater, ViewGroup container) {
        return inflate(R.layout.activity_custom_top_bar);
    }

    @Override
    public void findViewById() {
        mPager = findView(R.id.pager);
        root = findView(R.id.root);
        tv_custom_title = findView(R.id.tv_title);
    }

    @Override
    public void initData() {
        setShadowEnable(false);
        setSwipeBackEnable(false);
        root.setBackgroundColor(getNowColor(0));
        setStatusBarColor(Color.BLACK);
        setTopBarColor(getNowColor(0));
        tv_custom_title.setText("Card01");
        mPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin));
        mPager.setOffscreenPageLimit(3);
        List<CardFragment> fragments = new ArrayList<>();
        fragments.add(CardFragment.getInstance(new CardData("Card01")));
        fragments.add(CardFragment.getInstance(new CardData("Card02")));
        fragments.add(CardFragment.getInstance(new CardData("Card03")));
        fragments.add(CardFragment.getInstance(new CardData("Card04")));
        CardPagerAdapter cardPagerAdapter = new CardPagerAdapter(getSupportFragmentManager(),fragments);
        mPager.setAdapter(cardPagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                AnimatorUtils.showBackgroundColorAnimation(root, getPreColor(position), getNowColor(position), 400);
                AnimatorUtils.showBackgroundColorAnimation(getTopBarView(), getPreColor(position), getNowColor(position), 400);
//                setStatusBarColor(getNowColor(position));
                tv_custom_title.setText("Card0" + (position+1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int getPreColor(int position){
        switch (position){
            case 0:
                return ColorUtils.fourth_color;

            case 1:
                return ColorUtils.first_color;

            case 2:
                return ColorUtils.second_color;

            case 3:
                return ColorUtils.third_color;
        }
        return ColorUtils.fourth_color;
    }

    private int getNowColor(int position){
        switch (position){
            case 0:
                return ColorUtils.first_color;

            case 1:
                return ColorUtils.second_color;

            case 2:
                return ColorUtils.third_color;

            case 3:
                return ColorUtils.fourth_color;
        }
        return ColorUtils.fourth_color;
    }
}
