/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.yinhuidata.mytest.bean.MessageData;
import com.yinhuidata.mytest.yhao.floatwindow.FloatWindow;
import com.yinhuidata.mytest.yhao.floatwindow.MoveType;
import com.yinhuidata.mytest.yhao.floatwindow.Screen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author : mengxt
 * date   : 2018/8/31 下午4:33
 * desc   :
 */

public class FloatWindowManager {

    public static final String TAG = "FloatWindowManager";

    public static final String LEFT_BOTTOM = "1";
    public static final String RIGHT_BOTTOM = "2";
    //    public static final String NET_MSG = "3";
    //    public static final String BATTERY_MSG = "4";
    //    public static final String TEMPERATURE_MSG = "5";
    //    public static final String NIGHTMODE_MSG = "6";
    private boolean connectionFlag = false;
    private static boolean isFirst = true;
    List<MessageData> list = new ArrayList<>();
    LinkedList<MessageData> listTemp = new LinkedList<>();
    private RecycleViewAdapter recycleViewAdapter;
    private View view;
    private long lastTime;
    private int lastType;

    public static FloatWindowManager getInstance() {
        return LazyLoader.floatWindowManager;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "handleMessage: ");
            MessageData messageData;
            if (listTemp.size() == 0) {
                messageData = null;
            } else {
                messageData = listTemp.pop();
                if (messageData != null ) {
                    //不删除
                    if(!messageData.isDelete){
                        return;
                    }
                }
            }
            if (messageData != null) {
                list.remove(messageData);
                recycleViewAdapter.notifyDataSetChanged();
            }
            if (list.size() == 0) {
                hideRightTopTemperatureMsg();
            }
        }
    };

    private static class LazyLoader {
        public static final FloatWindowManager floatWindowManager = new FloatWindowManager();
    }

    private FloatWindowManager() {
        //        EventBus.getDefault().register(this);
        //       handler.sendEmptyMessage(0);

    }

    public void showRightTopTemperatureMsg(MessageData messageData) {
        if (isFirst) {
            listTemp.add(messageData);
            list.add(messageData);
            view = View.inflate(ContextManager.getContext(), R.layout.dialog_fragment, null);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
            recycleViewAdapter = new RecycleViewAdapter(ContextManager.getContext(), list);
            recyclerView.setLayoutManager(new LinearLayoutManager(ContextManager.getContext()));
            recyclerView.setAdapter(recycleViewAdapter);
            touchHelper.attachToRecyclerView(recyclerView);
            isFirst = false;
            handler.sendEmptyMessageDelayed(0, 5000);
        } else {

            Iterator<MessageData> iterator = list.iterator();
            while (iterator.hasNext()) {
                MessageData messageTemp = iterator.next();
                if (messageData.type == messageTemp.type) {
                    listTemp.remove(messageTemp);
                    iterator.remove();
                }
            }
            listTemp.add(messageData);
            list.add(0, messageData);
            recycleViewAdapter.notifyDataSetChanged();
            handler.sendEmptyMessageDelayed(0, 5000);
        }

        if (FloatWindow.get("one") != null && FloatWindow.get("one").isShowing()) {
            return;
        }
        if (FloatWindow.get("one") == null) {
            commonCreateFloatView(ContextManager.getContext(), view, 0.72f, 0.12f, false, "one",
                    MoveType.back, 0, 0).build();
        }
        FloatWindow.get("one").show();

    }
    public static final int NET_MSG = 1;

    public void deleteResidentNetFloatWindow() {

        Iterator<MessageData> iterator = list.iterator();
        while (iterator.hasNext()) {
            MessageData messageTemp = iterator.next();
            if (messageTemp.type == NET_MSG) {
                iterator.remove();
            }
        }
        recycleViewAdapter.notifyDataSetChanged();
        if (list.size() == 0) {
            hideRightTopTemperatureMsg();
        }

    }





    public void hideRightTopTemperatureMsg() {
        FloatWindow.get("one").hide();
    }

    //    public void showLeftBottomMsg() {
    //
    //        View view = View.inflate(ContextManager.getContext(), R.layout.connection_info_layout, null);
    //        WindowView windowView = DataBindingUtil.bind(view);
    //        windowView.setDatas(WinViewModel.getInstance());
    //        windowView.setFloatClick(this);
    //        windowView.setFloatRefresh(this);
    //        windowView.setIdentClick(this);
    //        if (FloatWindow.get(LEFT_BOTTOM) != null && FloatWindow.get(LEFT_BOTTOM).isShowing()) {
    //            return;
    //        }
    //        if (FloatWindow.get(LEFT_BOTTOM) != null && !FloatWindow.get(LEFT_BOTTOM).isShowing()) {
    //            FloatWindow.get(LEFT_BOTTOM).show();
    //            return;
    //
    //        }
    //        commonCreateFloatView(ContextManager.getContext(), view, 0.11f, 0.68f, false,
    //                LEFT_BOTTOM, MoveType.inactive, 0,
    //                0).setWidth(400).build();
    //
    //        FloatWindow.get(LEFT_BOTTOM).show();
    //
    //    }

    //    public void onIdentViewClick() {
    //        TextView identTv = FloatWindow.get(LEFT_BOTTOM).getView().findViewById(R.id.identTv);
    //        if (connectionFlag) {
    //            connectionFlag = false;
    //            identTv.setVisibility(View.INVISIBLE);
    //            return;
    //        }
    //        if (!connectionFlag) {
    //            connectionFlag = true;
    //            identTv.setVisibility(View.VISIBLE);
    //            return;
    //        }
    //    }

    //    public void onFloatRefreshClick() {
    //        SignalStation.getInstance().getConnectionId();
    //        SignalStation.getInstance().getConnectionStrength();
    //    }
    //
    //    public void onFloatViewClick() {
    //        LinearLayout connectionInfoLL = FloatWindow.get(LEFT_BOTTOM).getView().findViewById(R.id
    // .connectionInfoLL);
    //        if (connectionFlag) {
    //            connectionFlag = false;
    //            connectionInfoLL.setVisibility(View.INVISIBLE);
    //            return;
    //        }
    //        if (!connectionFlag) {
    //            connectionFlag = true;
    //            connectionInfoLL.setVisibility(View.VISIBLE);
    //            return;
    //        }
    //    }
    //
    //    public void hideLeftBottomMsg() {
    //        if (FloatWindow.get(LEFT_BOTTOM) != null && FloatWindow.get(LEFT_BOTTOM).isShowing()) {
    //            FloatWindow.get(LEFT_BOTTOM).hide();
    //        }
    //    }
    //
    //    public void showRightBottomMsg(View view, IClickListener iClickListener) {
    //        if (FloatWindow.get(RIGHT_BOTTOM) != null && FloatWindow.get(RIGHT_BOTTOM).isShowing()) {
    //            return;
    //        }
    //        if (FloatWindow.get(RIGHT_BOTTOM) != null && !FloatWindow.get(RIGHT_BOTTOM).isShowing()) {
    //            FloatWindow.get(RIGHT_BOTTOM).show();
    //            return;
    //        }
    //        commonCreateFloatView(ContextManager.getContext(), view, 0.85f, 0.8f, false,
    //                RIGHT_BOTTOM, MoveType.slide, 50, 50)
    //                .setMoveStyle(500, new BounceInterpolator())
    //                .setClickListener(iClickListener)
    //                .build();
    //        FloatWindow.get(RIGHT_BOTTOM).show();
    //
    //    }
    //
    //    public void hideRightBottomMsg() {
    //        if (FloatWindow.get(RIGHT_BOTTOM) != null && FloatWindow.get(RIGHT_BOTTOM).isShowing()) {
    //            FloatWindow.get(RIGHT_BOTTOM).hide();
    //        }
    //    }

    private FloatWindow.B commonCreateFloatView(Context context, View view, float screenWidth, float screenHeight,
                                                boolean desktopShow, String tag, int moveType, int slideLeftMargin,
                                                int slideRightMargin) {
        FloatWindow.B b = FloatWindow
                .with(context)
                .setView(view)
                .setX(Screen.width, screenWidth)
                .setY(Screen.height, screenHeight)
                .setDesktopShow(desktopShow)
                .setTag(tag)
                .setMoveType(moveType, slideLeftMargin, slideRightMargin);
        return b;
    }

    ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            //滑动时的一些操作
            //                isDelete = false;
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            // 处理滑动事件回调
            //                isDelete = true;
            // 判断方向，进行不同的操作
            if (direction == ItemTouchHelper.RIGHT) {
                final int pos = viewHolder.getAdapterPosition();
              final  MessageData messageData = list.get(pos);
                if(!messageData.isDelete){
                    return;
                }
                ObjectAnimator animator = ObjectAnimator.ofFloat(viewHolder.itemView, "translationX", 0.0f, 350.0f);
                animator.setDuration(500).start();
               animator.addListener(new AnimatorListenerAdapter() {
                   @Override
                   public void onAnimationEnd(Animator animation) {
                       super.onAnimationEnd(animation);
                       list.remove(messageData);
                       recycleViewAdapter.notifyItemRemoved(pos);
                   }
               });

                if (list.size() == 0) {

                    FloatWindow.get("one").hide();

                }
            } else {

            }
        }

        //处理动画
        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX,
                                float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //滑动时改变 Item 的透明度，以实现滑动过程中实现渐变效果
//                final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
//                viewHolder.itemView.setAlpha(alpha);
//                viewHolder.itemView.setTranslationX(dX);
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
        //滑动事件完成时回调
        //在这里可以实现撤销操作

        //是否长按进行拖拽
        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    });

}

