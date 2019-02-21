/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest.threerecycle.adapter;

import java.util.List;

import com.yinhuidata.mytest.R;
import com.yinhuidata.mytest.bean.ThreeData;
import com.yinhuidata.mytest.bean.ThreeDataNew;

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

public class RecycleTwoAdapter extends RecyclerView.Adapter<RecycleTwoAdapter.ViewHolderTwo>{

    private Context mContext;
    private List<ThreeDataNew.DataBean.Cata3Bean> mList;

    public RecycleTwoAdapter(Context context, List<ThreeDataNew.DataBean.Cata3Bean> list){
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolderTwo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.three_item_view, parent, false);

        return new ViewHolderTwo(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderTwo holder, int position) {
        holder.textView.setText(mList.get(position).cata_level2_name);
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

    class ViewHolderTwo extends RecyclerView.ViewHolder{

        private final TextView textView;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.three_tv);
        }
    }

}
