/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.yinhuidata.mytest.bean.JsonBean;
import com.yinhuidata.mytest.bean.ThreeDataNew;
import com.yinhuidata.mytest.dialog.KnowDialog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    public static final String TAG = Main2Activity.class.getSimpleName();
    private static boolean isLoaded = false;
    private ThreeDataNew threeDataNew;
    private View bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt2 = findViewById(R.id.bt2);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location1 = new int[2] ;
                bt2.getLocationInWindow(location1); //获取在当前窗口内的绝对坐标
//                bt2.getLocationOnScreen(location1); //获取在整个屏幕内的绝对坐标   这两个一样的值
                KnowDialog abc = new KnowDialog(location1[0], location1[1],0,bt2.getMeasuredHeight());
                abc.show(getSupportFragmentManager(), "abc");

            }
        });

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(MSG_LOAD_DATA);

            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoaded) {
                    showPickerView();
                } else {
                    Toast.makeText(Main2Activity.this, "Please waiting until the data is parsed", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

    }

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                Toast.makeText(Main2Activity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "data.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        //        try {
        Gson gson = new Gson();
        threeDataNew = gson.fromJson(result, ThreeDataNew.class);

        List<ThreeDataNew.DataBean.Cata3Bean> cata3Beans = compareId(threeDataNew.data.cata_3);

        HashMap<String, List<ThreeDataNew.DataBean.Cata3Bean>> stringListHashMap =
                assempleDataForListTwo(cata3Beans, threeDataNew.data.cata_3);

        HashMap<String, List<ThreeDataNew.DataBean.Cata3Bean>> stringListHashMapTwo =
                assempleDataForListThree(threeDataNew.data.cata_3, stringListHashMap);

        for (int i = 0; i < cata3Beans.size(); i++) {
            JsonBean jsonBean = new JsonBean();
            jsonBean.setName(cata3Beans.get(i).cata_level1_name);
            List<ThreeDataNew.DataBean.Cata3Bean> cata3Beans1 = stringListHashMap.get(cata3Beans.get(i).cata_level1_id);
            ArrayList<JsonBean.CityBean> cityBeans = new ArrayList<>();
            for (int j = 0; j < cata3Beans1.size(); j++) {
                JsonBean.CityBean cityBean = new JsonBean.CityBean();
                List<ThreeDataNew.DataBean.Cata3Bean> cata3Beans2 = stringListHashMapTwo.get(cata3Beans1.get(j));
                ArrayList<String> strings = new ArrayList<>();
                for (int k = 0; k < cata3Beans2.size(); k++) {
                    strings.add(cata3Beans2.get(k).cata_level3_name);
                }

                cityBean.setArea(strings);

                cityBean.setName(cata3Beans1.get(j).cata_level2_name);
                cityBeans.add(cityBean);

            }

            jsonBean.setCityList(cityBeans);
            detail.add(jsonBean);

        }

        //            JSONArray data = new JSONArray(result);
        //            Gson gson = new Gson();
        //            for (int i = 0; i < data.length(); i++) {
        //                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
        //                detail.add(entity);
        //            }
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        //        }
        return detail;
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

    public HashMap<String, List<ThreeDataNew.DataBean.Cata3Bean>> assempleDataForListTwo(List<ThreeDataNew.DataBean
            .Cata3Bean> list, List<ThreeDataNew
            .DataBean
            .Cata3Bean> totalList) {
        HashMap<String, List<ThreeDataNew.DataBean.Cata3Bean>> integerCata3BeanHashMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            ArrayList<ThreeDataNew.DataBean.Cata3Bean> cata3BeansList = new ArrayList<>();
            for (int j = 0; j < totalList.size(); j++) {
                if (list.get(i).cata_level1_id == totalList.get(j).cata_level1_id) {
                    cata3BeansList.add(totalList.get(j));
                }

            }
            integerCata3BeanHashMap.put(list.get(i).cata_level1_id, cata3BeansList);
        }

        return integerCata3BeanHashMap;

    }

    public HashMap<String, List<ThreeDataNew.DataBean.Cata3Bean>> assempleDataForListThree(
            List<ThreeDataNew.DataBean.Cata3Bean> listTotal, HashMap<String, List<ThreeDataNew.DataBean.Cata3Bean>>
            hashMapTwo) {
        HashMap<String, List<ThreeDataNew.DataBean.Cata3Bean>> integerCata3BeanHashMap = new HashMap<>();

        for (Map.Entry<String,List<ThreeDataNew.DataBean.Cata3Bean>> entry : hashMapTwo.entrySet()) {
            List<ThreeDataNew.DataBean.Cata3Bean> value = entry.getValue();

            for (int i = 0; i < value.size() ; i++) {
                List<ThreeDataNew.DataBean.Cata3Bean> cata3Beans = hashMapTwo.get(entry.getKey());
                ArrayList<ThreeDataNew.DataBean.Cata3Bean> cata3BeansList = new ArrayList<>();

                for (int j = 0; j < listTotal.size(); j++) {
                    if (value.get(i).cata_level2_id.equals(listTotal.get(j).cata_level2_id)) {
                        cata3BeansList.add(listTotal.get(j));
                    }

                }
            }




//            integerCata3BeanHashMap.put(str, cata3BeansList);

        }
        return integerCata3BeanHashMap;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

                        Toast.makeText(Main2Activity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    Toast.makeText(Main2Activity.this, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(Main2Activity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
