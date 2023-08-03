package com.pro.shop.mapper;
import java.util.List;
import com.pro.shop.VO.ComVO;

public interface ComMapper {
    
    List<ComVO> c_list(String b_idx) ; 

    void comment(ComVO vo);

    void delete(String c_idx);

}
