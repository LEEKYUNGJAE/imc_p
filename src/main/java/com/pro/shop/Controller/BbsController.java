package com.pro.shop.Controller;

import java.io.File;
import java.net.InetAddress;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pro.shop.Service.BbsService;
import com.pro.shop.VO.BbsVO;
import com.pro.shop.util.FileUtil;
import com.pro.shop.util.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;





@Controller
public class BbsController {
   
    Paging page;
    
    @Autowired
    private HttpSession session;

    @Autowired
	private HttpServletRequest request;

    @Autowired
    BbsService b_service;

    

    int nowPage;
	int totalCount;
	int numPerPage = 10;
	int pagePerBlok = 10;
    
    //자유 게시판 이동 
    @GetMapping("/bbs")
    public String bbslist(String cPage ,String category,String text ,String bname){

        if((session.getAttribute("bbs")) != null){
            session.removeAttribute("bbs");
        }
        if(session.getAttribute("page") != null){
            session.removeAttribute("page");
        }


        if(bname == null ){
            bname= "bbs";
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

       int startpage = page.getStartPage();
       int endpage = page.getEndPage();
        
       int begin = page.getBegin();
       int end = page.getEnd();

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
       
        session.setAttribute("page",page.getSb().toString());
        if(bbslist.size()>0){
            session.setAttribute("bbs",bbslist);
        }




       return "shop/bbs";
    }

    //문의 사항 페이지 이동
    @GetMapping("/inquire")
    public String inquire(String cPage ,String category,String text ,String bname){

        if((session.getAttribute("bbs")) != null){
            session.removeAttribute("bbs");
        }

        if(session.getAttribute("page") != null){
            session.removeAttribute("page");
        }

        if(bname == null ){
            bname= "inquire";
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

       int startpage = page.getStartPage();
       int endpage = page.getEndPage();
        
       int begin = page.getBegin();
       int end = page.getEnd();

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
       
        session.setAttribute("page",page.getSb().toString());
        if(bbslist.size()>0){
            session.setAttribute("bbs",bbslist);
        }




       return "shop/inquire";
    }
    //공지사항 페이지 이동
    @GetMapping("/usernotification")
    public String notification(String cPage ,String category,String text ,String bname){

        if((session.getAttribute("bbs")) != null){
            session.removeAttribute("bbs");
        }

        if(session.getAttribute("page") != null){
            session.removeAttribute("page");
        }

        if(bname == null ){
            bname= "usernotification";
        }

        if(cPage == null)
			nowPage = 1;
		else
			nowPage = Integer.parseInt(cPage);

        
        if(category!=null && text !=null){
            Map<String , String> map = new HashMap<> ();
            map.put("category",category);
            map.put("keyword",text);
            map.put("bname","notification");


            totalCount = b_service.searchcnt(map);
        }else{
            totalCount = b_service.bbs_cnt("notification");
        }

       page = new Paging(nowPage, totalCount, pagePerBlok, numPerPage, category , text, bname);

       int startpage = page.getStartPage();
       int endpage = page.getEndPage();
        
       int begin = page.getBegin();
       int end = page.getEnd();

       Map<String , Object > map = new HashMap<String,Object>();

       map.put("begin",begin);
       map.put("end",end);
       map.put("bname","notification");

       List<BbsVO> bbslist = new ArrayList<BbsVO> ();
       
       if(category != null && text != null){
        Map<String , String> map2 = new HashMap<> ();
        map2.put("begin", Integer.toString(begin));
        map2.put("end", Integer.toString(end));
        map2.put("bname", "notification");
        map2.put("category",category);
        map2.put("keyword",text);

        bbslist = b_service.searchlist(map2);

       }else if(category==null && text==null){

           bbslist = b_service.BbsList(map);
       }
       
        session.setAttribute("page",page.getSb().toString());
        if(bbslist.size()>0){
            session.setAttribute("bbs",bbslist);
        }

        return "shop/notification";  


    }

    //거래 게시판 이동
    @GetMapping("/transaction")
    public String Transaction (String cPage ,String category,String text ,String bname) {

        if((session.getAttribute("bbs")) != null){
            session.removeAttribute("bbs");
        }
        if(session.getAttribute("page") != null){
            session.removeAttribute("page");
        }

        if(bname == null ){
            bname= "transaction";
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

       page = new Paging(nowPage, totalCount, pagePerBlok, 5, category , text, bname);

       int startpage = page.getStartPage();
       int endpage = page.getEndPage();
        
       int begin = page.getBegin();
       int end = page.getEnd();

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
       
        session.setAttribute("page",page.getSb().toString());
        if(bbslist.size()>0){
            session.setAttribute("bbs",bbslist);
        }


        return "shop/transaction";
    }
    //문의 사항 글 쓰기
    @GetMapping("/inquirewrite")
    public String inquirewrite () {


        return "shop/inquirewrite";
    }

    //글 쓰기 페이지 이동
    @GetMapping("/write")
    public String write(){

        
        
        return "shop/write";
    }

    //글 내용에 들어가는 이미지를 저장하는 곳
    @RequestMapping(value="/saveImg", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String>saveImg(MultipartFile s_file) {

        Map<String , String> map = new HashMap<>();

        MultipartFile mf = s_file;

        String fname = null;

        String path2 = request.getContextPath();
		
		if(mf != null && mf.getSize() > 0) {
			//이미지 파일이 넘어온 경우
			// 이미지 파일을 저장할 곳의 절대경로가 필요함
			String realPath ="/static/editor_img/"; //저장 할 파일의 경로
            path2 = realPath;
		
			fname = mf.getOriginalFilename();
			
			// 이미지 파일이 이미 저장된 파일 이름과 동일할 경우는 알아서
			// 이름을 변경해야 한다. - FileRenameUtil
			fname = FileUtil.checkSameFileName(fname, realPath);
			
			try {
				mf.transferTo(new File(realPath, fname));//파일 업로드!!!!!
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		map.put("fname", fname);

        return  map;
    }
    //거래 게시판 글 쓰기
    @GetMapping("/transaction_write")
    public String Transaction_write () {


        return "shop/transaction_write";
    }


    
    //글쓰기를 하는 곳
    @RequestMapping(value="/write", method=RequestMethod.POST)
	public ModelAndView write(BbsVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();

        String realPath ="/static/file_upload/"; //저장 할 파일의 경로
            
        InetAddress localHost = InetAddress.getLocalHost();
        String ip = localHost.getHostAddress();


        vo.setIp(ip);


        String filename ;
        String ori_name;

        MultipartFile file = vo.getFile();

        if(file != null && file.getSize() > 0){

        

            filename =  file.getOriginalFilename();
            vo.setOri_name(filename);

            filename = FileUtil.checkSameFileName(filename, realPath);
            vo.setFile_name(filename);


            try {
				file.transferTo(new File(realPath, filename));//파일 업로드!!!!!
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

     
        if(vo.getPrice()== null){

            vo.setPrice("0");
        
        }

        b_service.write(vo);

        if((vo.getBname()).equals("transaction")){
            
            mv.setViewName("redirect:transaction");

        }else if((vo.getBname()).equals("notification")){

             mv.setViewName("redirect:notification");
        
        }else if((vo.getBname()).equals("inquire")){
            mv.setViewName("redirect:inquire");
            
        }else{
            mv.setViewName("redirect:bbs");
        }

	
		return mv;
	}


    //글 보기 
    @GetMapping("/view")
    public String view  (String b_idx) throws Exception{
        InetAddress localHost = InetAddress.getLocalHost();
        String ip = localHost.getHostAddress();
        String sessionIp = (String) session.getAttribute(b_idx);

        if(session.getAttribute("vo") != null){
            session.removeAttribute("vo");
        }


        if (sessionIp == null || !sessionIp.equals(ip)) {
            session.removeAttribute(b_idx);
            session.setAttribute(b_idx, ip);
            b_service.Hit(b_idx);
        }

        BbsVO vo = b_service.view(b_idx);

        session.setAttribute("vo", vo);

        if((vo.getBname()).equals("transaction")){

            return"shop/transaction_view";
        } 
        return "shop/view";
    }


    //글 수정 페이지 이동
    @PostMapping("/eidt")
    public String eidt(String b_idx,String bname){
       
        BbsVO vo = b_service.view(b_idx);

        session.setAttribute("vo", vo);

        if(bname != null && bname.equals("transaction"))
            return "shop/transaction_eidt";




        return "shop/eidt";
    }

    // 글 수정
    @RequestMapping(value="/goedit", method=RequestMethod.POST)
	public ModelAndView eidt(BbsVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();


        String realPath ="/static/file_upload/"; //저장 할 파일의 경로



        LocalDate now = LocalDate.now();
        String filename ;
        String ori_name;

        MultipartFile file = vo.getFile();

        if(file != null && file.getSize() > 0){

        

            filename =  file.getOriginalFilename();
            vo.setOri_name(filename);

            filename = FileUtil.checkSameFileName(filename, realPath);
            vo.setFile_name(filename);


            try {
				file.transferTo(new File(realPath, filename));//파일 업로드!!!!!
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

        b_service.edit(vo);

        if((vo.getBname()).equals("transaction")){
            mv.setViewName("redirect:/transaction");

        }else if((vo.getBname()).equals("inquire")){
            mv.setViewName("redirect:/inquire");

        }else if((vo.getBname()).equals("notification")){
             mv.setViewName("redirect:/notification");
        }else{

            mv.setViewName("redirect:/bbs");
        }
        
		
	
		return mv;
	}

    // 글 삭제 
    @PostMapping("/delete")
    public String delete(String b_idx, String bname){
       
        b_service.delete(b_idx);

        if(bname != null && bname.equals("transaction"))
            return "redirect:/transaction";

        if(bname != null && bname.equals("notification"))
            return "redirect:/notification";

        if(bname != null && bname.equals("inquire"))
            return "redirect:/inquire";
            
        if(bname != null && bname.equals("admininquire")){
            return "redirect:/admininquire";
        }
        
        if(bname != null && bname.equals("adminbbs"))
            return "redirect:/adminbbs";
            
        

        return "redirect:/bbs";
    }

}


