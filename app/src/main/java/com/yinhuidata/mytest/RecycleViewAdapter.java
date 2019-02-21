/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest;

import java.util.List;

import com.yinhuidata.mytest.bean.MessageData;
import com.yinhuidata.mytest.viewholder.ViewHolderFour;
import com.yinhuidata.mytest.viewholder.ViewHolderOne;
import com.yinhuidata.mytest.viewholder.ViewHolderThree;
import com.yinhuidata.mytest.viewholder.ViewHolderTwo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecycleViewAdapter extends RecyclerView.Adapter<BaseViewHolder<MessageData>> {
    private Context mContext;
    private List<MessageData> mList;

    public RecycleViewAdapter(Context context, List<MessageData> list) {

        mContext = context;
        mList = list;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.message_item, parent, false);
            ViewHolderOne holderOne = new ViewHolderOne(view);
            return holderOne;
        } else if (viewType == 2) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.message_item1, parent, false);
            ViewHolderTwo holderTwo = new ViewHolderTwo(view);
            return holderTwo;
        } else if (viewType == 3) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.message_item2, parent, false);
            ViewHolderThree holderThree = new ViewHolderThree(view);
            return holderThree;
        } else  {
            View view = LayoutInflater.from(mContext).inflate(R.layout.message_item3, parent, false);
            ViewHolderFour holderFour = new ViewHolderFour(view);
            return holderFour;
        }



    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(mContext,mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

}
