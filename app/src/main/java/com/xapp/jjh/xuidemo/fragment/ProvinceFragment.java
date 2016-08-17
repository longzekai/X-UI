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
import com.xapp.jjh.xuidemo.bean.Event;
import com.xapp.jjh.xuidemo.bean.Province;
import com.xapp.jjh.xuidemo.utils.AddressUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/15.
 * ------------------------------------
 */
public class ProvinceFragment extends LoadingFragment implements ListAdapter.OnItemClickListener{

    private RecyclerView mRecycler;

    private final int MSG_LOAD_OVER = 1001;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_LOAD_OVER:
                    list = (List<Province>) msg.obj;
                    ListAdapter listAdapter = new ListAdapter(mContext, list);
                    mRecycler.setAdapter(listAdapter);
                    listAdapter.setOnItemClickListener(ProvinceFragment.this);
                    setPageState(PageState.SUCCESS);
                    break;
            }
        }
    };
    private List<Province> list;

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout_list,container,false);
    }

    @Override
    public void findViewById() {
        mRecycler = findView(R.id.recycler);
    }

    @Override
    public void initData() {
        setPageState(PageState.LOADING);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        new Thread(){
            @Override
            public void run() {
                List<Province> provinces = AddressUtils.getProvinces();
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

    @Override
    public void onItemClick(RecyclerView.ViewHolder holder, int position) {
        Province province = list.get(position);
        EventBus.getDefault().post(new Event(Event.TYPE_LOCATION_CITY,province.getProvince_id()));
    }
}
