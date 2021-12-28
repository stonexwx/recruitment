package com.recruitment.global;

import java.util.ArrayList;
import java.util.List;

public class Contant {
    //一级岗位
    public static final String JOB1_NAME1="技术";
    public static final String JOB1_NAME2="产品";
    public static final String JOB1_NAME3="设计";
    public static final String JOB1_NAME4="运营";
    public static final String JOB1_NAME5="市场";
    public static final String JOB1_NAME6="人事/财务/行政";
    //二级岗位
    public static final String JOB2_NAME1="java";
    public static final String JOB2_NAME2="c++";
    public static final String JOB2_NAME3="产品经理";
    public static final String JOB2_NAME4="产品总监";
    public static final String JOB2_NAME5="UI设计师";
    public static final String JOB2_NAME6="平面设计师";
    public static final String JOB2_NAME7="新媒体运营";
    public static final String JOB2_NAME8="产品运营";
    public static final String JOB2_NAME9="人力资源专员";
    public static final String JOB2_NAME10="行政主管";
    public List<String> getPost1(){
        List<String> list =new ArrayList<>();
        list.add(JOB1_NAME1);
        list.add(JOB1_NAME2);
        list.add(JOB1_NAME3);
        list.add(JOB1_NAME4);
        list.add(JOB1_NAME5);
        list.add(JOB1_NAME6);
        return list;
    }
    public List<String> getPost2(){
        List<String> list =new ArrayList<>();
        list.add(JOB2_NAME1);
        list.add(JOB2_NAME2);
        list.add(JOB2_NAME3);
        list.add(JOB2_NAME4);
        list.add(JOB2_NAME5);
        list.add(JOB2_NAME6);
        list.add(JOB2_NAME7);
        list.add(JOB2_NAME8);
        list.add(JOB2_NAME9);
        list.add(JOB2_NAME10);
        return list;
    }
    //面试审核
    public static final String INTERVIEW_CREATED="新创建";
    public static final String INTERVIEW_SUBMIT="已提交";
    public static final String INTERVIEW_APPROVED="已审核";
    public static final String INTERVIEW_BACK="抱歉";
    public static final String INTERVIEW_RECHECK="待复核";
    public static final String INTERVIEW_PASS="通过";

}
