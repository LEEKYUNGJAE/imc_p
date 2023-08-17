package com.pro.shop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shop.VO.ChartVO;
import com.pro.shop.mapper.ChartMapper;

@Service
public class ChartService {
    
    @Autowired
    private ChartMapper c_Mapper;


    public ChartVO Chart(){


        return c_Mapper.chart();
    }


    public ChartVO NowJoin(){
        ChartVO vo = new ChartVO();
        

        vo = c_Mapper.NowJoin(); 

        System.out.println("vo:"+vo);

        return vo;
    }

    public ChartVO TotalMember(){
        
        ChartVO vo = c_Mapper.TotalMember();

        return vo;
    }

    public ChartVO withdrawal(){

        ChartVO vo = c_Mapper.withdrawal();

        return vo ; 
    }
    
    public List<ChartVO> DailyJoin(){


        List<ChartVO> list = c_Mapper.DailyJoin();

        return list;
    }

}
