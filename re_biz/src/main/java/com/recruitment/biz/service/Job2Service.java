package com.recruitment.biz.service;

import com.recruitment.dao.domain.Job2;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface Job2Service extends IService<Job2> {
    /**
     * 查询
     * @return
     */
    List<Job2> selectAll();
}
