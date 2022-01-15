package com.recruitment.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recruitment.dao.domain.Evaluation;
import com.recruitment.biz.service.EvaluationService;
import com.recruitment.dao.dto.EvaluationDTO;
import com.recruitment.dao.mapper.EvaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("evaluationService")
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements EvaluationService{
    @Autowired
    EvaluationMapper evaluationMapper;
    @Override
    public List<EvaluationDTO> evaAllSelect(Long eid) {
        return evaluationMapper.selectAllByEid(eid);
    }

    @Override
    public void evaAllInsert(Long eid, Evaluation evaluation) {
        evaluationMapper.insertAll(eid,evaluation);
    }

    @Override
    public void avg(Long eid) {
        String c = evaluationMapper.selectAvg();
        evaluationMapper.updateAvg(c.substring(0,4),eid);
    }

    /**
     * 评论审核
     *
     * @param id
     */
    @Override
    public void updateEvaluationAdmin(Long id) {
        evaluationMapper.updateEvaluationAdmin(id);
    }

    /**
     * 评论查看
     * @return
     * @param n
     * @param page
     */
    @Override
    public Map<String, Object> selectAllAdmin(int n, int page) {
        Map<String,Object> map = new HashMap<>();
        map.put("flag",true);
        map.put("data",evaluationMapper.selectAll((n-1)*page,page));
        map.put("count",evaluationMapper.selectContent());
        return map;
    }

    /**
     * 评论删除
     *
     * @param id
     */
    @Override
    public void deleteByidAdmin(Long id) {
        evaluationMapper.deleteById(id);
    }
}




