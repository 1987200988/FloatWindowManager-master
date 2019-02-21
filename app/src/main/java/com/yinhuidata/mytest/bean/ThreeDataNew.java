/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest.bean;

import java.util.List;

/**
 * Created by v_liwei10 on 2019/1/17.
 */

public class ThreeDataNew {

    /**
     * status : 0
     * message : success
     * data : {"cata_3":[{"id":"300018","name":"莫名急刹","parent_id":"300003","cata_level":"3","problem_module":"PNC",
     * "popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"5","cata_level1_id":"300000",
     * "cata_level2_id":"300003","cata_level3_id":"300018","cata_level1_name":"自动驾驶","cata_level2_name":"体感异常",
     * "cata_level3_name":"莫名急刹"},{"id":"200000","name":"有碰撞接管风险","parent_id":"300004","cata_level":"3",
     * "problem_module":"PNC","popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1",
     * "cata_level1_id":"300000","cata_level2_id":"300004","cata_level3_id":"200000","cata_level1_name":"自动驾驶",
     * "cata_level2_name":"车辆行为异常","cata_level3_name":"有碰撞接管风险"},{"id":"300019","name":"频繁点刹","parent_id":"300003",
     * "cata_level":"3","problem_module":"PNC","popup_visible":"0","trigger_visible":"0","feedback_visible":"0",
     * "weight":"1","cata_level1_id":"300000","cata_level2_id":"300003","cata_level3_id":"300019",
     * "cata_level1_name":"自动驾驶","cata_level2_name":"体感异常","cata_level3_name":"频繁点刹"},{"id":"300020","name":"其他",
     * "parent_id":"300003","cata_level":"3","problem_module":"PNC","popup_visible":"0","trigger_visible":"0",
     * "feedback_visible":"0","weight":"1","cata_level1_id":"300000","cata_level2_id":"300003",
     * "cata_level3_id":"300020","cata_level1_name":"自动驾驶","cata_level2_name":"体感异常","cata_level3_name":"其他"},
     * {"id":"300021","name":"规划到预设可行使区域以外","parent_id":"300004","cata_level":"3","problem_module":"PNC",
     * "popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1","cata_level1_id":"300000",
     * "cata_level2_id":"300004","cata_level3_id":"300021","cata_level1_name":"自动驾驶","cata_level2_name":"车辆行为异常",
     * "cata_level3_name":"规划到预设可行使区域以外"},{"id":"300022","name":"路线规划不合理","parent_id":"300004","cata_level":"3",
     * "problem_module":"PNC","popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1",
     * "cata_level1_id":"300000","cata_level2_id":"300004","cata_level3_id":"300022","cata_level1_name":"自动驾驶",
     * "cata_level2_name":"车辆行为异常","cata_level3_name":"路线规划不合理"},{"id":"300023","name":"到站地点于预设地点不符",
     * "parent_id":"300004","cata_level":"3","problem_module":"PNC","popup_visible":"0","trigger_visible":"0",
     * "feedback_visible":"0","weight":"1","cata_level1_id":"300000","cata_level2_id":"300004",
     * "cata_level3_id":"300023","cata_level1_name":"自动驾驶","cata_level2_name":"车辆行为异常",
     * "cata_level3_name":"到站地点于预设地点不符"},{"id":"300024","name":"泊车入库失败或位置不正","parent_id":"300004","cata_level":"3",
     * "problem_module":"PNC","popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1",
     * "cata_level1_id":"300000","cata_level2_id":"300004","cata_level3_id":"300024","cata_level1_name":"自动驾驶",
     * "cata_level2_name":"车辆行为异常","cata_level3_name":"泊车入库失败或位置不正"},{"id":"300026","name":"绿植入侵（人工驾驶可通行）",
     * "parent_id":"300004","cata_level":"3","problem_module":"PNC","popup_visible":"0","trigger_visible":"0",
     * "feedback_visible":"0","weight":"1","cata_level1_id":"300000","cata_level2_id":"300004",
     * "cata_level3_id":"300026","cata_level1_name":"自动驾驶","cata_level2_name":"车辆行为异常",
     * "cata_level3_name":"绿植入侵（人工驾驶可通行）"},{"id":"300027","name":"其他系统原因车辆不走","parent_id":"300004","cata_level":"3",
     * "problem_module":"ALL","popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1",
     * "cata_level1_id":"300000","cata_level2_id":"300004","cata_level3_id":"300027","cata_level1_name":"自动驾驶",
     * "cata_level2_name":"车辆行为异常","cata_level3_name":"其他系统原因车辆不走"},{"id":"300028","name":"偶现虚拟障碍物",
     * "parent_id":"300004","cata_level":"3","problem_module":"感知","popup_visible":"0","trigger_visible":"0",
     * "feedback_visible":"0","weight":"1","cata_level1_id":"300000","cata_level2_id":"300004",
     * "cata_level3_id":"300028","cata_level1_name":"自动驾驶","cata_level2_name":"车辆行为异常","cata_level3_name":"偶现虚拟障碍物"},
     * {"id":"300029","name":"其他异常行为","parent_id":"300004","cata_level":"3","problem_module":"ALL",
     * "popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1","cata_level1_id":"300000",
     * "cata_level2_id":"300004","cata_level3_id":"300029","cata_level1_name":"自动驾驶","cata_level2_name":"车辆行为异常",
     * "cata_level3_name":"其他异常行为"},{"id":"300030","name":"不合理变道","parent_id":"300004","cata_level":"3",
     * "problem_module":"PNC","popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1",
     * "cata_level1_id":"300000","cata_level2_id":"300004","cata_level3_id":"300030","cata_level1_name":"自动驾驶",
     * "cata_level2_name":"车辆行为异常","cata_level3_name":"不合理变道"},{"id":"300031","name":"开机自检故障","parent_id":"300005",
     * "cata_level":"3","problem_module":"功能安全","popup_visible":"0","trigger_visible":"0","feedback_visible":"0",
     * "weight":"1","cata_level1_id":"300000","cata_level2_id":"300005","cata_level3_id":"300031",
     * "cata_level1_name":"自动驾驶","cata_level2_name":"系统异常","cata_level3_name":"开机自检故障"},{"id":"300032",
     * "name":"C/D级故障","parent_id":"300005","cata_level":"3","problem_module":"功能安全","popup_visible":"0",
     * "trigger_visible":"0","feedback_visible":"0","weight":"1","cata_level1_id":"300000","cata_level2_id":"300005",
     * "cata_level3_id":"300032","cata_level1_name":"自动驾驶","cata_level2_name":"系统异常","cata_level3_name":"C/D级故障"},
     * {"id":"300033","name":"无法启动自动驾驶","parent_id":"300005","cata_level":"3","problem_module":"PNC",
     * "popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1","cata_level1_id":"300000",
     * "cata_level2_id":"300005","cata_level3_id":"300033","cata_level1_name":"自动驾驶","cata_level2_name":"系统异常",
     * "cata_level3_name":"无法启动自动驾驶"},{"id":"300017","name":"猛加速","parent_id":"300003","cata_level":"3",
     * "problem_module":"PNC","popup_visible":"0","trigger_visible":"0","feedback_visible":"0","weight":"1",
     * "cata_level1_id":"300000","cata_level2_id":"300003","cata_level3_id":"300017","cata_level1_name":"自动驾驶",
     * "cata_level2_name":"体感异常","cata_level3_name":"猛加速"},{"id":"300034","name":"无故退出自动驾驶","parent_id":"300005",
     * "cata_level":"3","problem_module":"PNC","popup_visible":"0","trigger_visible":"0","feedback_visible":"0",
     * "weight":"1","cata_level1_id":"300000","cata_level2_id":"300005","cata_level3_id":"300034",
     * "cata_level1_name":"自动驾驶","cata_level2_name":"系统异常","cata_level3_name":"无故退出自动驾驶"}]}
     */

    public int status;
    public String message;
    public DataBean data;

    public static class DataBean {
        public List<Cata3Bean> cata_3;

        public static class Cata3Bean {
            /**
             * id : 300018
             * name : 莫名急刹
             * parent_id : 300003
             * cata_level : 3
             * problem_module : PNC
             * popup_visible : 0
             * trigger_visible : 0
             * feedback_visible : 0
             * weight : 5
             * cata_level1_id : 300000
             * cata_level2_id : 300003
             * cata_level3_id : 300018
             * cata_level1_name : 自动驾驶
             * cata_level2_name : 体感异常
             * cata_level3_name : 莫名急刹
             */

            public String id;
            public String name;
            public String parent_id;
            public String cata_level;
            public String problem_module;
            public String popup_visible;
            public String trigger_visible;
            public String feedback_visible;
            public String weight;
            public String cata_level1_id;
            public String cata_level2_id;
            public String cata_level3_id;
            public String cata_level1_name;
            public String cata_level2_name;
            public String cata_level3_name;
            public boolean isClick;
            public String content;
        }
    }
}
