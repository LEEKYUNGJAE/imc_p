package com.pro.shop.mapper;

import java.util.List;

import com.pro.shop.VO.ChartVO;

public interface ChartMapper {

    ChartVO chart ();

    ChartVO NowJoin();
    
    ChartVO TotalMember();

    ChartVO withdrawal();

    List<ChartVO> DailyJoin();
}
