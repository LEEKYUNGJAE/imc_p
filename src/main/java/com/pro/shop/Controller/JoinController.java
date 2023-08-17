package com.pro.shop.Controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.pro.shop.Service.MemService;
import com.pro.shop.VO.MemVO;



import jakarta.servlet.http.HttpSession;

@Controller
public class JoinController {

    @Autowired
    private MemService m_service;

    @Autowired
    private HttpSession session;
    


    private String img_path = "/editor_img";

    //회원 가입 페이지 이동
    @GetMapping("/join")
    public String Main(Model model){

        return "shop/join";

    }
    
    //회원 가입 아이디 체크 
    @RequestMapping(value="/chkid", method=RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> chkid(@RequestParam("id") String id) {

        int chk = m_service.chkid(id);

        Map<String , Object> map = new HashMap<String,Object>() ;
        
        map.put("chk", chk);
        
        return map;
    }
    //회원 가입
    @RequestMapping(value="/join", method=RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> join (@RequestParam("userId") String userId, @RequestParam("password") String password,
         @RequestParam("name") String name,@RequestParam("birthdate") String birthdate,
            @RequestParam("gender") String gender,@RequestParam("tel") String tel) {
        //'userId' : userId , 'password' : password , 'name' : name , 'birthdate' : birthdate , 'gender' : gender
        

        MemVO vo = new MemVO();

        vo.setId(userId);
        vo.setPw(password);
        vo.setName(name);
        vo.setBirth(birthdate);
        vo.setGender(gender);
        vo.setTel(tel);
        vo.setActivate("0");

        m_service.join(vo);

        int chk = m_service.chkid(userId);

        Map<String , Object> map = new HashMap<String,Object>() ;
        
        map.put("chk", chk);
        
        return map;
    }

    @RequestMapping(value="/deletemember" , method=RequestMethod.POST)
    @ResponseBody
    public int DeleteMember (@RequestParam("m_idx") String m_idx){      
        int chk = 0;
        try {   
            //탈퇴하는 과정

            m_service.MemberDelete(m_idx);
            
            chk = 1;

            session.removeAttribute("member");

        } catch (Exception e) {}
        
        return chk ; 
    }

}
