package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.Enterprise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.recruitment.dao.domain.Enterprise
 */
public interface EnterpriseMapper extends BaseMapper<Enterprise> {
    /**
     * 根据招聘信息查询企业信息
     */
    Enterprise selectAllByRid(Long rid);

    /**
     * 企业排名查询
     */
    List<Enterprise> selectByEvaluation();

    /**
     * 企业搜索
     * @param message
     * @param type
     */
    List<Enterprise> selectAllByEnameAndEtype(@Param("message") String message,@Param("type") String type);
}




