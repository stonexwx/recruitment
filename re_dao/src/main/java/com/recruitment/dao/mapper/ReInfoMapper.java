package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.ReInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recruitment.dao.dto.ReinfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.recruitment.dao.domain.ReInfo
 */
public interface ReInfoMapper extends BaseMapper<ReInfo> {
    /**
     * 招聘信息全查询
     * @return List<ReInfo>
     */
    List<ReinfoDTO> selectAll(@Param("name") String name,@Param("address") String address,@Param("rid") Long rid);

}




