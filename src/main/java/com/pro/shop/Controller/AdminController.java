package com.pro.shop.Controller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.shop.Service.BbsService;
import com.pro.shop.Service.ChartService;
import com.pro.shop.Service.MemService;
import com.pro.shop.VO.BbsVO;
import com.pro.shop.VO.ChartVO;
import com.pro.shop.VO.MemVO;
import com.pro.shop.util.Paging;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    MemService m_Service;

    @Autowired
    HttpSession session;

    @Autowired
    BbsService b_service;
    
    @Autowired
    ChartService C_Service;
    
    Paging page;
    
    int nowPage;
	int totalCount;
	int numPerPage = 10;
	int pagePerBlok = 10;
    

    //어드민 로그인 페이지 이동 
    @GetMapping("/admin")
    public String AdminLogin (){


        return "admin/admin_login";

    }

    //로그인
    @RequestMapping(value="/admin/login", method=RequestMethod.POST)
    @ResponseBody
	public Map<String,String> AdminLogin (String id , String pw ){

        if((session.getAttribute("admin")) != null ){
            session.removeAttribute("admin");
        }
        
        Map<String,String> map = new HashMap<>();

        MemVO vo  = new MemVO();

        vo = m_Service.AdminLogin(id, pw);

            if(vo != null && vo.getActivate() != null){
                
            map.put("activate" , vo.getActivate());
            if(vo.getActivate().equals("1"))
                    session.setAttribute("admin", vo);
            }else{
    
                map.put("activate" , "0");
            }
        

        return map;
    }
    //회원 가입 페이지 이동
    @GetMapping("/adminjoin")
    public String adminjoin(){

        return "admin/admin_join";
    }

    //회원 가입
    @RequestMapping(value="/adminjoin", method=RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> adminjoin (@RequestParam("userId") String userId, @RequestParam("password") String password,
         @RequestParam("name") String name,@RequestParam("birthdate") String birthdate,
            @RequestParam("gender") String gender,@RequestParam("tel") String tel) {

        

        MemVO vo = new MemVO();

        vo.setId(userId);
        vo.setPw(password);
        vo.setName(name);
        vo.setBirth(birthdate);
        vo.setGender(gender);
        vo.setTel(tel);
        vo.setActivate("2");

        m_Service.join(vo);

        int chk = m_Service.chkid(userId);

        Map<String , Object> map = new HashMap<String,Object>() ;
        
        map.put("chk", chk);
        
        return map;
    }
    //어드민 페이지 메인
    @GetMapping("/admin/main")
    public String AdminMain() {
        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }
        if(session.getAttribute("chart")!= null){

            session.removeAttribute("chart");
        }

        ChartVO NowJoin = C_Service.NowJoin();

        ChartVO vo  = C_Service.Chart();
        
        ChartVO TotalMember = C_Service.TotalMember();

        ChartVO withdrawal =  C_Service.withdrawal();

        List<ChartVO> DailyJoin = C_Service.DailyJoin();


        session.setAttribute("chart", vo);
        session.setAttribute("NowJoin", NowJoin);
        session.setAttribute("TotalMember", TotalMember);
        session.setAttribute("withdrawal", withdrawal);
        session.setAttribute("DailyJoin", DailyJoin);
        return "admin/admin_main";
    }


    //공지사항 페이지
    @GetMapping("/notification")
    public String notification (String cPage ,String category,String text ,String bname) {

        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }

        if((session.getAttribute("bbs")) != null){
            session.removeAttribute("bbs");
        }
        if(session.getAttribute("page") != null){
            session.removeAttribute("page");
        }


        if(bname == null ){
            bname= "notification";
        }

        if(cPage == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(cPage);

        
        if(category!=null && text !=null){
            Map<String , String> map = new HashMap<> ();
            map.put("category",category);
            map.put("keyword",text);
            map.put("bname",bname);


            totalCount = b_service.searchcnt(map);
        }else{
            totalCount = b_service.bbs_cnt(bname);
        }

       page = new Paging(nowPage, totalCount, pagePerBlok, numPerPage, category , text, bname);


        
       int begin = page.getBegin();
       int end = page.getEnd();

       System.out.println(begin +"/"+ end);

       Map<String , Object > map = new HashMap<String,Object>();

       map.put("begin",begin);
       map.put("end",end);
       map.put("bname",bname);

       List<BbsVO> bbslist = new ArrayList<BbsVO> ();
       
       if(category != null && text != null){
        Map<String , String> map2 = new HashMap<> ();
        map2.put("begin", Integer.toString(begin));
        map2.put("end", Integer.toString(end));
        map2.put("bname", bname);
        map2.put("category",category);
        map2.put("keyword",text);

        bbslist = b_service.searchlist(map2);

       }else if(category==null && text==null){

           bbslist = b_service.BbsList(map);
       }
       
       if(bbslist.size()>0){
            session.setAttribute("page",page.getSb().toString());
            session.setAttribute("bbs",bbslist);
        }

        System.out.println(totalCount +"/"+ bbslist.size());
        return "admin/notification";
    }

    //어드민 문의사항 페이지
    @GetMapping("/admininquire")
    public String admininquire (String cPage ,String category,String text ,String bname) {

        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }

        if((session.getAttribute("bbs")) != null){
            session.removeAttribute("bbs");
        }
        if(session.getAttribute("page") != null){
            session.removeAttribute("page");
        }


        if(bname == null ){
            bname= "admininquire";
        }

        if(cPage == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(cPage);

        
        if(category!=null && text !=null){
            Map<String , String> map = new HashMap<> ();
            map.put("category",category);
            map.put("keyword",text);
            map.put("bname","inquire");


            totalCount = b_service.searchcnt(map);
        }else{
            totalCount = b_service.bbs_cnt("inquire");
        }

       page = new Paging(nowPage, totalCount, pagePerBlok, numPerPage, category , text, bname);


        
       int begin = page.getBegin();
       int end = page.getEnd();

       System.out.println(begin +"/"+ end);

       Map<String , Object > map = new HashMap<String,Object>();

       map.put("begin",begin);
       map.put("end",end);
       map.put("bname","inquire");

       List<BbsVO> bbslist = new ArrayList<BbsVO> ();
       
       if(category != null && text != null){
        Map<String , String> map2 = new HashMap<> ();
        map2.put("begin", Integer.toString(begin));
        map2.put("end", Integer.toString(end));
        map2.put("bname", "inquire");
        map2.put("category",category);
        map2.put("keyword",text);

        bbslist = b_service.searchlist(map2);

       }else if(category==null && text==null){

           bbslist = b_service.BbsList(map);
       }
       
       if(bbslist.size()>0){
            session.setAttribute("page",page.getSb().toString());
            session.setAttribute("bbs",bbslist);
        }

        System.out.println(totalCount +"/"+ bbslist.size());
        return "admin/inquire";
    }
    //어드민 글 작성 페이지 이동
    @GetMapping("/adminwrite")
    public String write (String type){

        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }

        System.out.println(type);

        return "admin/write";
    }

    //어드민 글 보기 페이지 이동
    @GetMapping("/adminview")
    public String Adminview( String b_idx )throws Exception {

        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }

        if(session.getAttribute("vo") != null){
            session.removeAttribute("vo");
        }

        InetAddress localHost = InetAddress.getLocalHost();
        String ip = localHost.getHostAddress();
        String sessionIp = (String) session.getAttribute(b_idx);


        if (sessionIp == null || !sessionIp.equals(ip)) {
            session.removeAttribute(b_idx);
            session.setAttribute(b_idx, ip);
            b_service.Hit(b_idx);
        }

        BbsVO vo = b_service.view(b_idx);

        session.setAttribute("vo", vo);


        return"admin/view";
        
    }
    //어드민 게시글 관리 페이지 이동
    @GetMapping("/adminbbs")
    public String AdminBbs (String cPage, String bname ,String category , String text) {

        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }


        if((session.getAttribute("bbs")) != null){
            session.removeAttribute("bbs");
        }

        if(session.getAttribute("page") != null){
            session.removeAttribute("page");
        }

        if(bname == null ){
            bname= "adminbbs";
        }

        if(cPage == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(cPage);

        
        if(category!=null && text !=null){
            Map<String , String> map = new HashMap<> ();
            map.put("category",category);
            map.put("keyword",text);

            totalCount = b_service.searchcnt(map);
        }else{
            totalCount = b_service.AdminCnt();
        }

       page = new Paging(nowPage, totalCount, pagePerBlok, numPerPage, category , text, bname);

       int startpage = page.getStartPage();
       int endpage = page.getEndPage();
        
       int begin = page.getBegin();
       int end = page.getEnd();

       Map<String , Object > map = new HashMap<String,Object>();

       map.put("begin",begin);
       map.put("end",end);

       List<BbsVO> bbslist = new ArrayList<BbsVO> ();
       
       if(category != null && text != null){
        Map<String , String> map2 = new HashMap<> ();
        map2.put("begin", Integer.toString(begin));
        map2.put("end", Integer.toString(end));
        map2.put("category",category);
        map2.put("keyword",text);

        bbslist = b_service.searchlist(map2);

       }else if(category==null && text==null){

           bbslist = b_service.AdminBbs(map);
       }
       
        session.setAttribute("page",page.getSb().toString());
        
        if(bbslist.size()>0){
            session.setAttribute("bbs",bbslist);
        }

        return "admin/bbs";

    }

    //어드민 공지사항 글 수정 페이지 이동
    @PostMapping("/admineidt")
    public String AdminEidt(String b_idx){

        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }
         if(session.getAttribute("vo") != null){
            session.removeAttribute("vo");
        }
        
        BbsVO vo = b_service.view(b_idx);

        session.setAttribute("vo", vo);

        return "admin/eidt";
    }

    //어드면 관리자 승 페이지 이동
    @GetMapping("/approval")
    public String Approval () {

        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }

        if((session.getAttribute("Approvallist"))!= null){

            session.removeAttribute("Approvallist");
        }

        List<MemVO> approvallist  = m_Service.Approvallist();

        if(approvallist.size() > 0){

            session.setAttribute("Approvallist", approvallist);
        }

        return "admin/approval";
    }

    //어드민 승인
    @RequestMapping(value="/approval", method=RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> Approval (@RequestParam("m_idx") String m_idx) {
        Map<String , Object> map = new HashMap<>();

        System.out.println("m_idx 는" + m_idx);
        
        int chk  = 0 ; 
        
        try {
            m_Service.approval(m_idx);
            
            chk=1;
            
        } catch (Exception e) {
            System.out.println("실패했단");
        }

        map.put("chk", chk);

        return map;

    }

    //회원 관리 페이지
    @GetMapping("/member")
    public String Member ( String bname , String cPage , String category ,String text) {
        if(session.getAttribute("admin") == null){
            return "redirect:/admin";
        }

        if((session.getAttribute("Admin_member")) != null){
            session.removeAttribute("Admin_member");
        }

        if(session.getAttribute("page") != null){
            session.removeAttribute("page");
        }

        Map<String , String> map = new HashMap<> ();

        if(bname == null ){
            bname= "member";
        }

        if(cPage == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(cPage);

        
        if(category!=null && text !=null){
            
            map.put("category",category);
            map.put("keyword",text);

        }
            totalCount = m_Service.MemberCnt(map);
 

       page = new Paging(nowPage, totalCount, pagePerBlok, 5, category , text, bname);

       int startpage = page.getStartPage();
       int endpage = page.getEndPage();
        
       int begin = page.getBegin();
       int end = page.getEnd();


       map.put("begin",Integer.toString(begin));
       map.put("end",Integer.toString(end));

       List<MemVO> MemList = new ArrayList<MemVO> ();
       
        MemList = m_Service.MemberList(map);
        System.out.println(MemList);
  
       if(totalCount>5)
            session.setAttribute("page",page.getSb().toString());
        
        if(MemList.size()>0){
            session.setAttribute("Admin_member",MemList);
        }
        return "admin/member";
    } 
    //회원 탈퇴
    @RequestMapping(value="/memberdel", method=RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> MemberDelete (@RequestParam("m_idx") String m_idx) {
        Map<String , Object> map = new HashMap<>();

        System.out.println("m_idx 는" + m_idx);
        
        int chk  = m_Service.MemberDelete(m_idx);

        map.put("chk", chk);

        return map;

    }
}
