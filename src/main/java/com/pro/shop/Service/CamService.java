package com.pro.shop.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shop.VO.CamVO;
import com.pro.shop.mapper.CamMapper;


@Service
public class CamService {
    
    @Autowired
    private CamMapper C_Mapper;


    public void Update (CamVO vo) {

        C_Mapper.AddData(vo);
    }

    public void Del_Data(){

        C_Mapper.del_data();
    }

    public int CamCnt (Map<String, Object> map) {
        int totalCount = 0 ;

        totalCount = C_Mapper.cam_cnt(map);

        return totalCount;
    } 
    

    public List<CamVO> CamList (Map<String,Object> map ){

        List<CamVO> list = new ArrayList<>();


        list = C_Mapper.cam_list(map);

        return list;
    }

    public List<CamVO> Rendom () {
 
        List<CamVO> rendom = C_Mapper.rendom();

        return rendom;
    }
}
