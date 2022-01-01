package com.recruitment.biz.service;

import com.recruitment.global.QueryAll;

import java.util.List;

public interface Query {
    /**
     * 综合查询
     */
    QueryAll select(String message, String se_type, String type);
}
