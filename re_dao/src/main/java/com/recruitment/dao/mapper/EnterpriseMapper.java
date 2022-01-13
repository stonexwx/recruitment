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
    Enterprise selectAllByRid(@Param("rid") Long rid,@Param("type") String type);

    /**
     * 企业排名查询
     */
    List<Enterprise> selectByEvaluation();

    /**
     * 企业搜索
     *
     * @param message
     * @param type
     */
    List<Enterprise> selectAllByEnameAndEtype(@Param("message") String message, @Param("type") String type);

    /*
      管理员分隔线————————————————————————————————————————————————————————————————————————————
     */

    /**
     * 管理员企业添加
     */
    void insertAll(@Param("enterprise") Enterprise enterprise);

    /**
     * 管理员查询
     * @param page
     * @param limit
     */
    List<Enterprise> selectAll(@Param("page") int page,@Param("limit") int limit);

    /**
     * 管理员修改
     */
    void updateAll(@Param("enterprise") Enterprise enterprise);

    /**
     * 管理员删除
     */
    void deleteByEid(@Param("eid") Long eid);
}




