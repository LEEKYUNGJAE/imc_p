package com.pro.shop.VO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BbsVO {

    String b_idx,subject, m_idx, content, file_name, ori_name,write_date, ip, hit, bname, like, status, price;


    //member에 id 값    
    String id ;

    MultipartFile file;

    List<ComVO> c_list ;
    
}
