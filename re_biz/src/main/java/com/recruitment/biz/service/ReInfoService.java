package com.recruitment.biz.service;

import com.recruitment.dao.domain.ReInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recruitment.dao.dto.ReinfoDTO;

import java.util.List;

/**
 *
 */
public interface ReInfoService extends IService<ReInfo> {
    /**
     * 招聘信息全查询
     * @return List<ReInfo>
     */
    List<ReinfoDTO> selectALL();

}
