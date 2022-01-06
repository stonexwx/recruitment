package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.Job2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.recruitment.dao.domain.Job2
 */
public interface Job2Mapper extends BaseMapper<Job2> {
    /**
     * 查询全部工作
     */
    List<Job2> selectAll();
}




