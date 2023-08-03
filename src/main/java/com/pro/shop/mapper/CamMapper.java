package com.pro.shop.mapper;

import java.util.List;
import java.util.Map;

import com.pro.shop.VO.CamVO;

public interface CamMapper {

    void AddData(CamVO vo);

    void del_data();

    int cam_cnt (Map<String, Object> map);
    
    List<CamVO> cam_list(Map<String, Object> map);

    List<CamVO> rendom  () ;
}
