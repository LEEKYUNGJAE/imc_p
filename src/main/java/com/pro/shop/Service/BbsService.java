package com.pro.shop.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shop.VO.BbsVO;
import com.pro.shop.mapper.BbsMapper;

@Service
public class BbsService {

    @Autowired
    private BbsMapper b_Mapper;

    public void write (BbsVO vo) {
    
        b_Mapper.write(vo);

    }

    public List<BbsVO> BbsList (Map<String,Object> map) {

        List<BbsVO> bbslist = b_Mapper.bbslist(map);

        return bbslist;
    }

    public int bbs_cnt (String bname) {

        int bbs_cnt = b_Mapper.bbs_cnt(bname);

        return bbs_cnt;

    }

    public BbsVO view (String m_idx) { 

        BbsVO vo = b_Mapper.view(m_idx);

        return vo;
    }



    public void edit (BbsVO vo) {

        if(vo.getB_idx() !=null){

            b_Mapper.edit(vo);
        }

    }

    public void delete(String b_idx){

        b_Mapper.delete(b_idx);
    }
    
    public int searchcnt(Map<String,String> map){

        int cnt ;

        cnt = b_Mapper.searchcnt(map);

        return cnt;
    }

    public List<BbsVO> searchlist (Map<String,String> map) {

        List<BbsVO> bbslist = b_Mapper.searchlist(map);

        return bbslist;
    }

    public List<BbsVO> BestBbs () {

        List<BbsVO> BestBbs = b_Mapper.bestbbs();

        return BestBbs;
    }

    public List<BbsVO> BestTransaction () {

        List<BbsVO> BestTransaction = b_Mapper.besttransaction();

        return BestTransaction;
    }



    public void Hit (String b_idx) {

        b_Mapper.hit(b_idx);
    }

    public int AdminCnt (){

        return b_Mapper.admincnt();
    }

    public List<BbsVO> AdminBbs(Map<String, Object > map  ) {

        List<BbsVO> list = b_Mapper.adminbbs(map);

        return list ; 
    }




}
