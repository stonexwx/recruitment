package com.recruitment.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.domain.Evaluation;
import com.recruitment.dao.dto.EvaluationDTO;

import java.util.List;

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
}
