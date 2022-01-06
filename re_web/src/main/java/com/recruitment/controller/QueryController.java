package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.Queryimpl;
import com.recruitment.global.QueryAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QueryController {
    @Autowired
    Queryimpl queryimpl;
    @RequestMapping("/query")
    @ResponseBody
    public String query(String message,String se_type,String city ){
        List<QueryAll> list = queryimpl.select(message,se_type,city);
       return JSON.toJSONString(list);
    }
}
