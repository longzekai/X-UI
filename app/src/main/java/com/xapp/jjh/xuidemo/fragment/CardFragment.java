package com.xapp.jjh.xuidemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xapp.jjh.xui.fragment.LoadingFragment;
import com.xapp.jjh.xuidemo.FourthActivity;
import com.xapp.jjh.xuidemo.R;
import com.xapp.jjh.xuidemo.bean.CardData;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/17.
 * ------------------------------------
 */
public class CardFragment extends LoadingFragment {

    private TextView tv_card;

    public static CardFragment getInstance(CardData cardData){
        CardFragment fragment = new CardFragment();
        Bundle localBundle = new Bundle();
        localBundle.putSerializable(CardData.KEY_CARD_DATA, cardData);
        fragment.setArguments(localBundle);
        return fragment;
    }

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout_card,container,false);
    }

    @Override
    public void findViewById() {
        tv_card = findView(R.id.tv_card);
    }

    @Override
    public void initData() {
        CardData cardData = (CardData) getArguments().getSerializable(CardData.KEY_CARD_DATA);
        tv_card.setText(cardData.getMessage());
    }

    @Override
    public void setListener() {
        tv_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FourthActivity.class);
                startActivity(intent);
            }
        });
    }
}
