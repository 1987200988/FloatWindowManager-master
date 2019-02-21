/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest.dialog;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yinhuidata.mytest.ContextManager;
import com.yinhuidata.mytest.GetJsonDataUtil;
import com.yinhuidata.mytest.Main2Activity;
import com.yinhuidata.mytest.R;
import com.yinhuidata.mytest.RecyclerViewClickListener2;
import com.yinhuidata.mytest.bean.ThreeData;
import com.yinhuidata.mytest.bean.ThreeDataNew;
import com.yinhuidata.mytest.threerecycle.adapter.RecycleOneAdapter;
import com.yinhuidata.mytest.threerecycle.adapter.RecycleThreeAdapter;
import com.yinhuidata.mytest.threerecycle.adapter.RecycleTwoAdapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by v_liwei10 on 2018/6/15.
 */

@SuppressLint("ValidFragment")
public class KnowDialog extends BaseDialogFragment {

    private IsKnow mIsKnow;
    private AlertDialog mDialog;
    private String mContent;
    private int screenWidth;
    private int screenHeight;
    private WindowManager.LayoutParams params;
    private Window window;
    private View view;

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private static List<ThreeDataNew.DataBean.Cata3Bean> listOne = new ArrayList<>();
    private static List<ThreeDataNew.DataBean.Cata3Bean> listTwo = new ArrayList<>();
    private static List<ThreeDataNew.DataBean.Cata3Bean> listThree = new ArrayList<>();
    private int listIndexOne;
    private int listIndexTwo;
    private int listIndexThree;
    private RecycleTwoAdapter recycleTwoAdapter;
    private RecycleThreeAdapter recycleThreeAdapter;
    private RecycleOneAdapter recycleOneAdapter;
    private ThreeDataNew threeDataNew;
    private int x;
    private int y;
    private int width;
    private int height;

    //    static {
    //        listOne.add(new ThreeData("one"));
    //        listOne.add(new ThreeData("two"));
    //        listOne.add(new ThreeData("three"));
    //        listOne.add(new ThreeData("four"));
    //        listOne.add(new ThreeData("five"));
    //        listOne.add(new ThreeData("six"));
    //    }
    //
    //    static {
    //        listTwo.add(new ThreeData("1"));
    //        listTwo.add(new ThreeData("2"));
    //        listTwo.add(new ThreeData("3"));
    //        listTwo.add(new ThreeData("4"));
    //        listTwo.add(new ThreeData("5"));
    //        listTwo.add(new ThreeData("6"));
    //    }
    //
    //    static {
    //        listThree.add(new ThreeData("a"));
    //        listThree.add(new ThreeData("b"));
    //        listThree.add(new ThreeData("c"));
    //        listThree.add(new ThreeData("d"));
    //        listThree.add(new ThreeData("e"));
    //        listThree.add(new ThreeData("f"));
    //    }

    public KnowDialog(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog_dark_background);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_know, null);

        builder.setView(view);
        mDialog = builder.create();
        mDialog.setCanceledOnTouchOutside(false);

        window = mDialog.getWindow();
        params = window.getAttributes();
        params.windowAnimations = R.style.BottomDialogAnimation;

        //        screenWidth = (int) getResources().getDimension(R.dimen.neo_dialog_width);
        //        screenHeight = (int) getResources().getDimension(R.dimen.neo_dialog_height);

        params.width = 400;
        params.height = 400;
        int i = this.y + this.height;
        Log.e(TAG, "onCreateDialog: "+this.y+"height=="+this.height );
        //        window.setGravity(Gravity.CENTER_HORIZONTAL);
        params.x = 0;
        params.y = this.y+this.height;
        window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);

        window.setAttributes(params);

        //        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        //            //竖屏
        //            absoluteDistanceY = (int) getResources().getDimension(R.dimen
        //                    .neo_notice_dialog_margin_top);
        //            params.y = absoluteDistanceY;
        //            params.x = 0;
        //            window.setAttributes(params);
        //            window.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
        //        } else {
        //            //横屏
        //            absoluteDistanceX = (int) getResources().getDimension(R.dimen
        //                    .neo_notice_dialog_margin_left);
        //            params.y = 0;
        //            params.x = absoluteDistanceX;
        //            window.setAttributes(params);
        //            window.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        //
        //        }
        initView(view);

        return mDialog;
    }

    private void initView(View view) {

        String JsonData = new GetJsonDataUtil().getJson(getActivity(), "data.json");//获取assets目录下的json文件数据
        threeDataNew = new Gson().fromJson(JsonData, ThreeDataNew.class);
        List<ThreeDataNew.DataBean.Cata3Bean> cata3Beans = compareId(threeDataNew.data.cata_3);
        listOne.addAll(cata3Beans);

        recyclerView1 = (RecyclerView) view.findViewById(R.id.recycle1);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recycle2);
        recyclerView3 = (RecyclerView) view.findViewById(R.id.recycle3);

        recycleOneAdapter = new RecycleOneAdapter(getActivity(), listOne);
        recyclerView1.setLayoutManager(new LinearLayoutManager(ContextManager.getContext()));
        recyclerView1.setAdapter(recycleOneAdapter);

        recyclerView1.addOnItemTouchListener(new RecyclerViewClickListener2(getActivity(), recyclerView1,
                new RecyclerViewClickListener2.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        for (int i = 0; i < listOne.size(); i++) {
                            if (i == position) {
                                listOne.get(i).isClick = true;
                                listIndexOne = position;
                            } else {
                                listOne.get(i).isClick = false;
                            }
                        }
                        recycleOneAdapter.notifyDataSetChanged();
                        //issue 点击第一列表第三个列表都不会显示，因为第三个是根据第二个来展示数据?
                        listTwo.clear();
                        listThree.clear();

                        //第一种情况点击条目无数据情况
                        String cata_level1_id = listOne.get(position).cata_level1_id;
                        List<ThreeDataNew.DataBean.Cata3Bean> cata_3 = compareIdTwo(threeDataNew.data.cata_3);
                        for (int i = 0; i < cata_3.size(); i++) {
                            if (cata_level1_id.equals(cata_3.get(i).cata_level1_id)) {
                                listTwo.add(cata_3.get(i));
                            }
                        }

                        //                        if(listOne.get(position).content.equals("one")){
                        //                            listTwo.clear();
                        //                        }
                        //                        //第二种情况点击条目有数据情况
                        //                        else if(listOne.get(position).content.equals("two")){
                        //                            listTwo.clear();
                        //                            listTwo.add(new ThreeData("1"));
                        //                            listTwo.add(new ThreeData("2"));
                        //                            listTwo.add(new ThreeData("3"));
                        //                            listTwo.add(new ThreeData("4"));
                        //                            listTwo.add(new ThreeData("5"));
                        //                            listTwo.add(new ThreeData("6"));
                        //
                        //                        }else {
                        //                            listTwo.clear();
                        //                        }

                        //                  todo       根据position来改变第二个列表的数据源也就是listTwo
                        if (recycleTwoAdapter == null) {
                            recycleTwoAdapter = new RecycleTwoAdapter(getActivity(), listTwo);
                            recyclerView2.setLayoutManager(new LinearLayoutManager(ContextManager.getContext()));
                            recyclerView2.setAdapter(recycleTwoAdapter);
                        } else {
                            recycleTwoAdapter.notifyDataSetChanged();
                        }

                        if (recycleThreeAdapter == null) {
                            recycleThreeAdapter = new RecycleThreeAdapter(getActivity(), listThree);
                            recyclerView3.setLayoutManager(new LinearLayoutManager(ContextManager.getContext()));
                            recyclerView3.setAdapter(recycleThreeAdapter);
                        } else {
                            recycleThreeAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));

        recyclerView2.addOnItemTouchListener(new RecyclerViewClickListener2(getActivity(), recyclerView2,
                new RecyclerViewClickListener2.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        for (int i = 0; i < listTwo.size(); i++) {
                            if (i == position) {
                                listIndexTwo = position;
                                listTwo.get(i).isClick = true;
                            } else {
                                listTwo.get(i).isClick = false;
                            }
                        }

                        recycleTwoAdapter.notifyDataSetChanged();

                        listThree.clear();

                        String cata_level2_id = listTwo.get(position).cata_level2_id;
                        List<ThreeDataNew.DataBean.Cata3Bean> cata_3 = compareIdThree(threeDataNew.data.cata_3);
                        for (int i = 0; i < cata_3.size(); i++) {
                            if (cata_level2_id.equals(cata_3.get(i).cata_level2_id)) {
                                listThree.add(cata_3.get(i));
                            }
                        }

                        //                        //第一种情况点击条目无数据情况
                        //                        if(listTwo.get(position).content.equals("1")){
                        //                            listThree.clear();
                        //                        }
                        //                        //第二种情况点击条目有数据情况
                        //                        else if(listTwo.get(position).content.equals("2")){
                        //                            listThree.clear();
                        //                            listThree.add(new ThreeData("a"));
                        //                            listThree.add(new ThreeData("b"));
                        //                            listThree.add(new ThreeData("c"));
                        //                            listThree.add(new ThreeData("d"));
                        //                            listThree.add(new ThreeData("e"));
                        //                            listThree.add(new ThreeData("f"));
                        //
                        //                        }else {
                        //                            listThree.clear();
                        //                        }

                        //                        todo  根据position来改变第三个列表

                        if (recycleThreeAdapter == null) {
                            recycleThreeAdapter = new RecycleThreeAdapter(getActivity(), listThree);
                            recyclerView3.setLayoutManager(new LinearLayoutManager(ContextManager.getContext()));
                            recyclerView3.setAdapter(recycleThreeAdapter);
                        } else {
                            recycleThreeAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));

        recyclerView3.addOnItemTouchListener(new RecyclerViewClickListener2(getActivity(), recyclerView3,
                new RecyclerViewClickListener2.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        listIndexThree = position;
                        Toast.makeText(getActivity(), "选择了" + listOne.get(listIndexOne).content + listTwo.get
                                (listIndexTwo).content + listThree.get(listIndexThree).content, Toast.LENGTH_SHORT)
                                .show();

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));

    }

    //

    public void setSureInterface(IsKnow isKnow) {
        mIsKnow = isKnow;
    }

    public interface IsKnow {
        public void isknow();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public List<ThreeDataNew.DataBean.Cata3Bean> compareId(List<ThreeDataNew.DataBean.Cata3Bean> list) {
        List<String> stringList = new ArrayList<String>();
        ArrayList<ThreeDataNew.DataBean.Cata3Bean> cata3BeansList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            if (!stringList.contains(list.get(i).cata_level1_id)) {
                stringList.add(list.get(i).cata_level1_id);
                cata3BeansList.add(list.get(i));
            }

        }
        return cata3BeansList;

    }

    public List<ThreeDataNew.DataBean.Cata3Bean> compareIdTwo(List<ThreeDataNew.DataBean.Cata3Bean> list) {
        List<String> stringList = new ArrayList<String>();
        ArrayList<ThreeDataNew.DataBean.Cata3Bean> cata3BeansList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            if (!stringList.contains(list.get(i).cata_level2_id)) {
                stringList.add(list.get(i).cata_level2_id);
                cata3BeansList.add(list.get(i));
            }

        }
        return cata3BeansList;

    }

    public List<ThreeDataNew.DataBean.Cata3Bean> compareIdThree(List<ThreeDataNew.DataBean.Cata3Bean> list) {
        List<String> stringList = new ArrayList<String>();
        ArrayList<ThreeDataNew.DataBean.Cata3Bean> cata3BeansList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            if (!stringList.contains(list.get(i).cata_level3_id)) {
                stringList.add(list.get(i).cata_level3_id);
                cata3BeansList.add(list.get(i));
            }

        }
        return cata3BeansList;

    }
}
