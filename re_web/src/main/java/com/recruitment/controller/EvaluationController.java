package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.EvaluationServiceImpl;
import com.recruitment.dao.domain.Evaluation;
import com.recruitment.dao.dto.EvaluationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EvaluationController {
    @Autowired
    EvaluationServiceImpl evaluationService;
    /**
     * 查询
     */
    @RequestMapping("/evaluation_select")
    @ResponseBody
    public String eva_select(String eid){
        List<EvaluationDTO> evaluations = evaluationService.evaAllSelect(Long.valueOf(eid));
        return JSON.toJSONString(evaluations);
    }
}
