package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.Evaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.recruitment.dao.dto.EvaluationDTO;
import org.apache.ibatis.annotations.Param;
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
    /**
     * 录入
     */
    void insertAll(@Param("eid") Long eid,@Param("evaluation") Evaluation evaluation);
    /**
     * 计算平均值
     */
    String selectAvg();
    /**
     * 保存平均值
     */
    void updateAvg(@Param("avg") String avg,@Param("eid") Long eid);
    /**
     * 评论审核
     */
    void updateEvaluationAdmin(@Param("id") Long id);
    /**
     * 评论查看
     * @param n
     * @param page
     */
    List<Evaluation> selectAll(@Param("n") int n,@Param("page") int page);
    /**
     * 评论删除
     * @param id
     */
    void deleteById(@Param("id") Long id);
    /**
     * 查询记录数
     */
    int selectContent();
}




