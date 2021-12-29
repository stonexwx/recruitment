package com.recruitment.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.recruitment.dao.domain.Evaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.recruitment.dao.domain.Evaluation
 */
@Repository("ev")
public interface EvaluationMapper extends BaseMapper<Evaluation> {

}




