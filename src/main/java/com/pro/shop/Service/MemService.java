package com.pro.shop.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shop.VO.MemVO;
import com.pro.shop.mapper.MemMapper;

@Service
public class MemService {

    @Autowired
    private MemMapper m_mapper ; 

    public int chkid( String id ) {    

    int chk = 0 ; 



    List<MemVO> list = m_mapper.chkid(id);

    if(list.size()>0){
        chk=1;
    }

    return chk;
    }

    public void join( MemVO vo ) {

    m_mapper.join(vo); 
    
    }

    public MemVO login ( String id , String pw  ) {

        MemVO member  = m_mapper.login(id, pw);

        return member;
    
    }

    public void change_pw (String m_idx , String pw ) {
        m_mapper.change_pw(m_idx, pw);
    }


    public void change_tel (String m_idx , String tel ) {
        m_mapper.change_tel(m_idx, tel);
    }

    public MemVO AdminLogin (String id , String pw ) {

        MemVO vo  = new MemVO();

        if(id.trim().length() > 0 && pw.trim().length()>0){

            vo =m_mapper.AdminLogin(id, pw);
    
        }

        return vo ;
    }

    public List<MemVO> Approvallist () {

        List<MemVO> approvallist = m_mapper.approvallist();
        
        
        return approvallist ;
    }

    public void approval (String m_idx) {

        m_mapper.approval(m_idx);

    }

    public int MemberCnt (Map<String, String> map ){

        int cnt  = 0;

        cnt = m_mapper.MemberCnt(map);

        return cnt;
    }
    public List<MemVO> MemberList (Map<String , String> map){

        List<MemVO> list = m_mapper.MemberList(map);

        return list;
    }

    public int MemberDelete(String m_idx){
       int chk = 0 ;
        try {
            m_mapper.MemberDelete(m_idx);
            chk=1;

        } catch (Exception e) {}

        return chk;
    }
    
}
