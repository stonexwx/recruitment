package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.EvaluationServiceImpl;
import com.recruitment.dao.domain.Evaluation;
import com.recruitment.dao.domain.Users;
import com.recruitment.dao.dto.EvaluationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 添加平均分数
     * @return
     */
    @RequestMapping("/evaluation_insert")
    @ResponseBody
    public String eva_insert(String eid, String score, String textarea, HttpSession httpSession){
        Users users = (Users) httpSession.getAttribute("user");
        Evaluation evaluation =new Evaluation();
        Date date = new Date();
        evaluation.setContent(textarea);
        evaluation.setScore(score);
        evaluation.setType("0");
        evaluation.setAddtime(date);
        evaluation.setUid(users.getUid());
        evaluationService.evaAllInsert(Long.valueOf(eid),evaluation);
        evaluationService.avg(Long.valueOf(eid));
        Map<String,Boolean> map = new HashMap<>();
        map.put("flag",true);
        return JSON.toJSONString(map);
    }
}
