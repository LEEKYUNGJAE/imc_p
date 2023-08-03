package com.pro.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pro.shop.VO.MemVO;

public interface MemMapper {

    List<MemVO> chkid(@Param("id") String id);

    void join (MemVO vo);

    MemVO login(@Param("id") String id , @Param("pw") String pw ) ;

    void change_pw(@Param("m_idx") String m_idx , @Param("pw") String pw );
    
    void change_tel(@Param("m_idx") String m_idx , @Param("tel") String tel );

    MemVO AdminLogin(@Param("id") String id , @Param("pw") String pw) ;
    
    List<MemVO> approvallist ();

    void approval (String m_idx);

    int MemberCnt(Map<String,String> map);
    
    List<MemVO> MemberList (Map<String,String> map);

    void MemberDelete (String m_idx);
}
