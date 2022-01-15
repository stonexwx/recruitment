package com.recruitment.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.domain.Evaluation;
import com.recruitment.dao.dto.EvaluationDTO;

import java.util.List;
import java.util.Map;

public interface EvaluationService extends IService<Evaluation> {
    /**
     * 查询
     * @return
     */
    List<EvaluationDTO> evaAllSelect(Long eid);
    /**
     * 评价录入
     */
    void evaAllInsert(Long eid, Evaluation evaluation);
    /**
     * 计算平均值
     * @param eid
     */
    void avg(Long eid);
    /*-----------------------------------我是分割线-------------------------------------*/
    /**
     * 评论审核
     */
    void updateEvaluationAdmin(Long id);
    /**
     * 评论查看
     * @return
     * @param n
     * @param page
     */
    Map<String,Object> selectAllAdmin(int n,int page);
    /**
     * 评论删除
     */
    void deleteByidAdmin(Long id);
}
