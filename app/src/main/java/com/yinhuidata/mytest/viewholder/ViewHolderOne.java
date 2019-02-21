/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest.viewholder;

import com.yinhuidata.mytest.BaseViewHolder;
import com.yinhuidata.mytest.R;
import com.yinhuidata.mytest.bean.MessageData;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by v_liwei10 on 2018/11/21.
 */

public class ViewHolderOne extends BaseViewHolder<MessageData> {
    public static final String TAG = ViewHolderOne.class.getSimpleName();
    private View mRootView;

    public ViewHolderOne(View itemView) {
        super(itemView);
        mRootView = itemView;

    }

    @Override
    public void setData(final Context context, final MessageData messageData) {
        setOnClickListener(R.id.message_bt, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ");
                Toast.makeText(context,""+messageData.type+messageData.content,Toast.LENGTH_SHORT).show();
            }
        });
        setText(R.id.message_bt,messageData.content);



    }
}
