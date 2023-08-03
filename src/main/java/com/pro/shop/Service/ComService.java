package com.pro.shop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shop.VO.ComVO;
import com.pro.shop.mapper.ComMapper;

@Service 
public class ComService {

    @Autowired
    private ComMapper c_Mapper;

    public int comment(ComVO vo){
        int chk  = 0;

        try {
            c_Mapper.comment(vo);

            chk=1;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return chk;
    }


     public int delete(String c_idx){
        int chk  = 0;

        try {
            c_Mapper.delete(c_idx);
            chk=1;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return chk;
    }
    
}
