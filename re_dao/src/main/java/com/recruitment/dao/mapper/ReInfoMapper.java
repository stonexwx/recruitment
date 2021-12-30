package com.recruitment.dao.mapper;

import com.recruitment.dao.domain.ReInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recruitment.dao.dto.ReinfoDTO;

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
    List<ReinfoDTO> selectAll();
    /**
     * 招聘信息按照条件进行查询
     */
    List<ReInfo> selectChose(Map<String,String> map);

}




