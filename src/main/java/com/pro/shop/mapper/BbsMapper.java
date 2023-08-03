package com.pro.shop.mapper;

import java.util.List;
import java.util.Map;

import com.pro.shop.VO.BbsVO;

public interface BbsMapper {

    void write(BbsVO vo);

    List<BbsVO> bbslist (Map<String,Object> map);
    
    int bbs_cnt (String bname);   
    
    BbsVO view(String m_idx);
    
    void edit (BbsVO vo);

    void delete (String b_idx);

    int  searchcnt (Map<String, String> map);

    List<BbsVO> bestbbs ();
    
    List<BbsVO> besttransaction();
 
    List<BbsVO> searchlist(Map<String, String> map);

    void hit(String b_idx);

    List<BbsVO> adminbbs (Map<String, Object> map);

    int admincnt();


}
