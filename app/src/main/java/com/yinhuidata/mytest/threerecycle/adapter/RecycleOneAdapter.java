/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest.threerecycle.adapter;

import java.util.List;

import com.yinhuidata.mytest.R;
import com.yinhuidata.mytest.bean.ThreeData;
import com.yinhuidata.mytest.bean.ThreeDataNew;
import com.yinhuidata.mytest.viewholder.ViewHolderOne;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by v_liwei10 on 2019/1/11.
 */

public class RecycleOneAdapter extends RecyclerView.Adapter<RecycleOneAdapter.ViewHolderOne> {

    private Context mContext;
    private List<ThreeDataNew.DataBean.Cata3Bean> mList;

    public RecycleOneAdapter(Context context, List<ThreeDataNew.DataBean.Cata3Bean> list){
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolderOne onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.three_item_view, parent, false);

        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderOne holder, int position) {
        notifyDataSetChanged();
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
            }
        });
        holder.textView.setText(mList.get(position).cata_level1_name);
        if(mList.get(position).isClick){
            holder.textView.setTextColor(Color.BLUE);
        }else {
            holder.textView.setTextColor(Color.BLACK);

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolderOne extends RecyclerView.ViewHolder{
        private  TextView textView;

        public ViewHolderOne(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.three_tv);
        }
    }
}
