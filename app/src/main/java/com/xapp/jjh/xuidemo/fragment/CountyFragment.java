package com.xapp.jjh.xuidemo.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xapp.jjh.xui.fragment.LoadingFragment;
import com.xapp.jjh.xui.inter.PageState;
import com.xapp.jjh.xuidemo.R;
import com.xapp.jjh.xuidemo.adapter.ListAdapter;
import com.xapp.jjh.xuidemo.bean.City;
import com.xapp.jjh.xuidemo.bean.County;
import com.xapp.jjh.xuidemo.utils.AddressUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/15.
 * ------------------------------------
 */
public class CountyFragment extends LoadingFragment{

    private RecyclerView mRecycler;

    private int id = -1;

    private final int MSG_LOAD_OVER = 1001;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_LOAD_OVER:
                    list = (List<County>) msg.obj;
                    listAdapter = new ListAdapter(mContext, list,id);
                    mRecycler.setAdapter(listAdapter);
                    mRecycler.post(new Runnable() {
                        @Override
                        public void run() {
                            int pos = getPos();
                            scrollToPosition(pos);
                        }
                    });
                    setPageState(PageState.SUCCESS);
                    break;
            }
        }
    };
    private List<County> list = new ArrayList<>();
    private ListAdapter listAdapter;

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout_list,container,false);
    }

    @Override
    public void findViewById() {
        mRecycler = findView(R.id.recycler);
    }

    public void setId(int id){
        this.id = id;
        if(list.size()>0){
            int pos = getPos();
            listAdapter.setId(id);
            scrollToPosition(pos);
        }
    }

    private int getPos() {
        int pos = 0;
        for(int i=0;i<list.size();i++){
            County city = list.get(i);
            if(id == city.getCity_id()){
                pos = i;
                break;
            }
        }
        return pos;
    }

    private void scrollToPosition(int pos){
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecycler.getLayoutManager();
        linearLayoutManager.scrollToPositionWithOffset(pos,0);
    }

    @Override
    public void initData() {
        setPageState(PageState.LOADING);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        new Thread(){
            @Override
            public void run() {
                List<County> provinces = AddressUtils.getCounty(0,false);
                Message msg = Message.obtain();
                msg.what = MSG_LOAD_OVER;
                msg.obj = provinces;
                handler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public void setListener() {

    }
}