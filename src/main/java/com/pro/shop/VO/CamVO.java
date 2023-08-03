package com.pro.shop.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamVO {

    private String idx, title, category, mapX, mapY, addr, explain, minutely, lunchtime, facilities, manner, animal,
    image, tel, page, cname;
    
}
