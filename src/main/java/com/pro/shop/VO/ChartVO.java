package com.pro.shop.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartVO {


    //이용 현황
    private int member , comment , bbs , transaction , inquire ; 
    //최근 가입 현황
    private String NowJoin , YesterDayJoin; 

    //최근 5일
    private String Date ,DailyJoin;


    
}
