package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.ReInfoServiceImpl;
import com.recruitment.dao.dto.ReinfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8081/")
public class ReinfoConller {
    @Autowired
    ReInfoServiceImpl reInfoService;

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(value = "/reinfoAll",method = RequestMethod.POST)
    @ResponseBody
    public String reinfoSelect(){
        List<ReinfoDTO> list =reInfoService.selectALL();
        return JSON.toJSONString(list);
    }
    /**
     * 根据id查询
     */
    @RequestMapping(value = "/reinfoAllByID")
    @ResponseBody
    public String reinfoSelectById(Long rid){
        List<ReinfoDTO> list =reInfoService.selectALLById(rid);
        return JSON.toJSONString(list.get(0));
    }
}
