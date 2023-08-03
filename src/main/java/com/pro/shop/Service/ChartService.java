package com.pro.shop.Service;

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


}
