package com.xapp.jjh.xuidemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xapp.jjh.xuidemo.R;
import com.xapp.jjh.xuidemo.bean.Area;
import com.xapp.jjh.xuidemo.bean.City;
import com.xapp.jjh.xuidemo.bean.County;
import com.xapp.jjh.xuidemo.bean.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/15.
 * ------------------------------------
 */
public class ListAdapter<T extends Area> extends RecyclerView.Adapter<ListAdapter.ItemHolder>{

    private Context mContext;
    private List<T> mList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private int id = -1;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public ListAdapter(Context context, List<T> list){
        this.mContext = context;
        this.mList = list;
    }

    public ListAdapter(Context context, List<T> list, int id){
        this.mContext = context;
        this.mList = list;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(View.inflate(mContext,R.layout.item_area,null));
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        Area area = mList.get(position);
        holder.tv_name.setTextColor(Color.parseColor("#555555"));
        holder.tv_name.getPaint().setFakeBoldText(false);
        if(area instanceof Province){
            holder.tv_name.setText(((Province)area).getProvince_name() + "(" + area.getSpell_case() + ")");
        }else if(area instanceof City){
            City city = (City)area;
            holder.tv_name.setText(city.getCity_name() + "(" + area.getSpell_case() + ")");
            if(city.getProvince_id() == id){
                setLightText(holder.tv_name);
            }
        }else if(area instanceof County){
            County county = (County)area;
            holder.tv_name.setText(county.getCounty_name() + "(" + area.getSpell_case() + ")");
            if(county.getCity_id() == id){
                setLightText(holder.tv_name);
            }
        }
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder,position);
                }
            });
        }
    }

    private void setLightText(TextView view){
        view.getPaint().setFakeBoldText(true);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{

        TextView tv_name;

        public ItemHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(RecyclerView.ViewHolder holder,int position);
    }

}
