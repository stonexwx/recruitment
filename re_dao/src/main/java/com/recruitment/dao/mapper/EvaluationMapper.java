package com.recruitment.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.recruitment.dao.domain.Evaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.recruitment.dao.domain.Evaluation
 */
public interface EvaluationMapper extends BaseMapper<Evaluation> {
    List<Evaluation> selectList();
}




