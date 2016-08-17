package com.xapp.jjh.xuidemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xapp.jjh.xuidemo.fragment.CardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/17.
 * ------------------------------------
 */
public class CardPagerAdapter extends FragmentStatePagerAdapter {

    private List<CardFragment> fragments = new ArrayList<>();

    public CardPagerAdapter(FragmentManager fragmentManager, List<CardFragment> fragments){
        super(fragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Card" + position;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
