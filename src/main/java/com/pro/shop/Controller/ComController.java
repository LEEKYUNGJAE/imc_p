package com.pro.shop.Controller;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.shop.Service.ComService;
import com.pro.shop.VO.ComVO;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ComController {

    @Autowired
    private ComService c_Service;


    @Autowired
	private HttpServletRequest request;

    //댓글 작성
    @RequestMapping(value="/comment", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Integer> Comment(ComVO vo) throws Exception {

        Map<String , Integer> map = new HashMap<>();

        InetAddress localHost = InetAddress.getLocalHost();      
        String ip = localHost.getHostAddress();

        vo.setIp(ip);

        int chk = c_Service.comment(vo);

        map.put("chk" , chk);

        return  map;
    }

    //댓글 삭제
    @RequestMapping(value="/comment_del", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Integer> comment_del(String c_idx){

        Map<String , Integer> map = new HashMap<>();

        System.out.println("여기요");
        System.out.println(c_idx);

        int chk = c_Service.delete(c_idx);

        map.put("chk" , chk);

        return  map;
    }
}
