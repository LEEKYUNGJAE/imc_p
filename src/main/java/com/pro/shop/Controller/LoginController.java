package com.pro.shop.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.shop.Service.MemService;
import com.pro.shop.VO.MemVO;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
    
    @Autowired
    private MemService m_service;
    
    @Autowired
    private HttpSession session;

    //로그인 페이지 이동
    @GetMapping("/login")
    public String login (){

        return "shop/login";
    }
    //로그인 
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> login (@RequestParam("id") String id, @RequestParam("pw") String pw ) {
    //로그인 버튼 클릭 시 실행되는 컨트롤러

        Map<String , Object> map = new HashMap<String,Object>() ;
        
        int chk = 1 ; 

        MemVO member = m_service.login(id, pw);

    // id pw 가 일치 하는 경우 session에 회원 정보를 저장한다 
        if(member != null && member.getId() !=null){

            chk= 0;

            session.removeAttribute("member");
            session.setAttribute("member",member);
        }
        
        map.put("MemberChk", chk);

        return map;    
    }
    

    //비밀 번호 확인
    @RequestMapping(value="/change_pw", method=RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> change_pw (@RequestParam("m_idx") String m_idx, @RequestParam("id") String id , @RequestParam("pw") String pw ) {

        Map<String , Object> map = new HashMap<String,Object>() ;
        
        m_service.change_pw(m_idx, pw);
        
        
        int chk = 1 ; 

        MemVO member = m_service.login(id, pw);

    // id pw 가 일치 하는 경우 session에 회원 정보를 저장한다 
        if(member != null && member.getId() !=null){
            chk= 0;
        }
        
        map.put("MemberChk", chk);

        return map;    
    }
    //전화번호 변경
    @RequestMapping(value="/change_tel", method=RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> change_tel (@RequestParam("m_idx") String m_idx, @RequestParam("tel") String tel ) {

        Map<String , Object> map = new HashMap<String,Object>() ;

        int chk = 1 ; 
        
        try {
            m_service.change_tel(m_idx, tel);
            chk = 0;

        } catch (Exception e) {
            System.out.println("휴대폰 번호 변경 실패");
            
        }

        map.put("MemberChk", chk);
        
        return map;    
    }

    //로그 아웃
    @GetMapping("/logout")
    public String logout (){

        if(session.getAttribute("member") != null){
            session.removeAttribute("member");
        }

         if(session.getAttribute("admin") != null){
            session.removeAttribute("admin");

            return "admin/admin_login";
        }

        
        return "shop/main";
    }
    //마이 페이지 이동
    @GetMapping("/mypage")
    public String MyPage (){

        return "shop/mypage";

    }

}
