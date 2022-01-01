package com.recruitment.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.recruitment.dao.domain.Evaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.recruitment.dao.dto.EvaluationDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.recruitment.dao.domain.Evaluation
 */
@Repository()
public interface EvaluationMapper extends BaseMapper<Evaluation> {
    /**
     * 查询
     */
    List<EvaluationDTO> selectAllByEid(Long eid);
}




