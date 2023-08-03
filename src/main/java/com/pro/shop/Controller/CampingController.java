    package com.pro.shop.Controller;

    import java.net.HttpURLConnection;
    import java.net.URL;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    import org.jdom2.Document;
    import org.jdom2.Element;
    import org.jdom2.input.SAXBuilder;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;

    import com.pro.shop.Service.CamService;
    import com.pro.shop.VO.CamVO;
    import com.pro.shop.util.Paging;

    import jakarta.servlet.http.HttpSession;

    @Controller
    public class CampingController {

        @Autowired
        private CamService C_Service;

        @Autowired
        private HttpSession session;

        Paging paging;
        
        int totalCount ; 
        int nowPage ;
        int pagePerBlok= 10;
        int numPerPage = 5;
        int begin;
        int end;


        //캠핑 페이지 이동
        @GetMapping("/gocamping")
        public String GoCamping (String cPage ,String type, String region, String category){

            session.removeAttribute("list");
            
            session.removeAttribute("page");

            if(cPage == null)
                nowPage = 1;
            else
                nowPage = Integer.parseInt(cPage);
        
            if(type == null){
                type="gocamping";
            }

            if(region != null  && category != null){

                Map<String,Object> map = new HashMap<>();    

                map.put("region", region);
                map.put("category", category);   

                totalCount = C_Service .CamCnt(map);


                paging = new Paging(nowPage, totalCount, pagePerBlok, numPerPage,region, category , "gocamping");

                begin = paging.getBegin();
                end = paging.getEnd();
                
                map.put("begin", begin);
                map.put("end", end);

                List<CamVO> list = C_Service.CamList(map);


                if(list.size() > 0){

                    session.setAttribute("list",list);
                    if(totalCount > 5)
                        session.setAttribute("page",paging.getSb().toString());
                }

                

            }

            return "shop/gocamping";
        }
        

        //캠핑 데이터 
        @PostMapping("/update")
        public String Update1() throws Exception {

            C_Service.Del_Data();

            String API_URL = "http://apis.data.go.kr/B551011/GoCamping/basedList?serviceKey=wBQmzgiBACZ1O%2FJ79%2FmqTep2O%2BGJZiXE2%2BHacF5l2epi%2FT4f1uh270dxEAiIzwstnzWTF74b6C%2BRli%2BRL4UlTQ%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=xml";
            URL url = new URL(API_URL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            SAXBuilder builder = new SAXBuilder();

            Document doc = builder.build(conn.getInputStream());

            Element root = doc.getRootElement();

            System.out.println(root.getName());
            Element body = root.getChild("body");

            String tcnt = body.getChildText("totalCount");

            int totalCount = (int) Math.ceil(Integer.parseInt(tcnt) / 100);

            for (int i = 0; i <= totalCount; i++) {

                String count = String.valueOf(i + 1);
                URL url2 = new URL(
                        "http://apis.data.go.kr/B551011/GoCamping/basedList?serviceKey=wBQmzgiBACZ1O%2FJ79%2FmqTep2O%2BGJZiXE2%2BHacF5l2epi%2FT4f1uh270dxEAiIzwstnzWTF74b6C%2BRli%2BRL4UlTQ%3D%3D&numOfRows=100&pageNo="
                                + count + "&MobileOS=ETC&MobileApp=AppTest&_type=xml");
                HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();

                conn2.connect();

                Document doc2 = builder.build(conn2.getInputStream());

                Element root2 = doc2.getRootElement();

                Element body2 = root2.getChild("body");

                Element items = body2.getChild("items");

                List<Element> item_list = items.getChildren("item");
                for (Element item : item_list) {
                    String idx = "";
                    String title = item.getChildText("facltNm");
                    String category = item.getChildText("induty");
                    String addr = item.getChildText("addr1");
                    String mapY = item.getChildText("mapY");
                    String mapX = item.getChildText("mapX");
                    String lunchtime = "";
                    String explain = item.getChildText("lineIntro");
                    String minutely = item.getChildText("intro");
                    String facilities = item.getChildText("sbrsCl");
                    String manner = item.getChildText("resveCl");
                    String animal = item.getChildText("animalCmgCl");
                    String image = item.getChildText("firstImageUrl");
                    String tel = item.getChildText("tel");
                    String page = item.getChildText("homepage");
                    String cname = "캠핑";
                    CamVO vo = new CamVO(idx, title, category, addr, mapY, mapX, explain, minutely, lunchtime, facilities,
                            manner, animal, image, tel, page, cname);

                    C_Service.Update(vo);
                }
            }
            return "shop/gocamping";
        }

        
    }
