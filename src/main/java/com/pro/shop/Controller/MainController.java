package com.pro.shop.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.pro.shop.Service.BbsService;
import com.pro.shop.Service.CamService;
import com.pro.shop.VO.BbsVO;
import com.pro.shop.VO.CamVO;

import jakarta.servlet.http.HttpSession;




@Controller
public class MainController {

    @Autowired
    private BbsService b_Service;

    @Autowired
    private CamService c_Service;

    @Autowired
    private HttpSession session;


    @GetMapping("/")
    public String Main(){
       
        if (session.getAttribute("rendom") != null){
            session.removeAttribute("rendom");
        }
        
        if (session.getAttribute("BestBbs") != null){
            session.removeAttribute("BestBbs");
        }
        
        if (session.getAttribute("BestTransaction") != null){
            session.removeAttribute("BestTransaction");
        }
        List<CamVO> rendom = c_Service.Rendom();
        List<BbsVO> BestBbs = b_Service.BestBbs();
        List<BbsVO> BestTransaction =b_Service.BestTransaction();




        session.setAttribute("rendom", rendom);

        session.setAttribute("BestBbs", BestBbs);

        session.setAttribute("BestTransaction", BestTransaction);
        return "shop/main";
    }

    
}
