package com.recruitment.controller;

import com.alibaba.fastjson.JSON;
import com.recruitment.biz.service.impl.EnterpriseServiceImpl;
import com.recruitment.biz.service.impl.EvaluationServiceImpl;
import com.recruitment.biz.service.impl.Queryimpl;
import com.recruitment.dao.domain.Enterprise;
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
    @Autowired
    EnterpriseServiceImpl enterpriseService;
    @Autowired
    Queryimpl queryimpl;
    /**
     * 查询
     */
    @RequestMapping("/evaluation_select")
    @ResponseBody
    public String eva_select(String eid){
        String a;
        if (eid.contains("e")) {
            a=eid.substring(1);
        }else {
            Enterprise enterprise = enterpriseService.selectByRid(Long.valueOf(eid), "putong");
            a= String.valueOf(enterprise.getEid());
        }
        List<EvaluationDTO> evaluations = evaluationService.evaAllSelect(Long.valueOf(a));
        return JSON.toJSONString(evaluations);
    }
    /**
     * 添加平均分数
     * @return
     */
    @RequestMapping("/evaluation_insert")
    @ResponseBody
    public String eva_insert(String ename, String score, String textarea, HttpSession httpSession){
        Users users = (Users) httpSession.getAttribute("user");
        Evaluation evaluation =new Evaluation();
        Date date = new Date();
        evaluation.setContent(textarea);
        evaluation.setScore(score);
        evaluation.setType("0");
        evaluation.setAddtime(date);
        evaluation.setUid(users.getUid());
        Long eid=queryimpl.select(ename,"1",null).get(0).getEid();
        evaluationService.evaAllInsert(eid,evaluation);
        evaluationService.avg(eid);
        Map<String,Boolean> map = new HashMap<>();
        map.put("flag",true);
        return JSON.toJSONString(map);
    }
    /*----------------------------------------分隔线---------------------------------------------*/
    /**
     * 评论审核
     */
    @RequestMapping("/admin_evaluation_update")
    @ResponseBody
    public String adminEva_update(Long id){
        evaluationService.updateEvaluationAdmin(id);
        Map<String,Boolean> map = new HashMap<>();
        map.put("flag",true);
        return JSON.toJSONString(map);
    }
    /**
     * 评论查看
     */
    @RequestMapping("/admin_evaluation_select")
    @ResponseBody
    public String adminEva_select(){
       return JSON.toJSONString(evaluationService.selectAllAdmin()) ;
    }
    /**
     * 评论删除
     */
    @RequestMapping("/admin_evaluation_delete")
    @ResponseBody
    public String adminEva_delete(Long id){
        evaluationService.deleteByidAdmin(id);
        Map<String,Boolean> map = new HashMap<>();
        map.put("flag",true);
        return JSON.toJSONString(map);
    }
}
