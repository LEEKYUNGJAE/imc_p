package com.pro.shop.util;

public class AdminPaging {
	
	int nowPage = 1; //현재 페이지

	int numPerPage = 10;// 한 페이지당 보여질 게시물 수
	int totalCount = 0;// 총 게시물의 수	
	int pagePerBlock = 5;//한 블럭당 보여질 페이지 수(페이지 묶음)
	int totalPage = 0;// 전체 페이지 수
	
	int begin,end,startPage,endPage;
	//	첫 게시물,마지막 게시물,시작 페이지,마지막 페이지
	
	boolean isPrePAge ; //이전 기능 가능여부 (true일때 이전기능 활성화)
	boolean isNextPage; //다음 기능 가능여부
	
	//list.jsp에서 표현할 페이징 HTML코드를 저장할 것
	private StringBuffer sb;
	
	public AdminPaging() {
	//기본 생성자	
	}
	public AdminPaging(int numPerPage ,int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
		this.numPerPage = numPerPage;
	}
	
	public AdminPaging(int nowPage,int totalCount,int pagePerBlock, int numPerPage, String catagory , String text ,String bname) {
		this.nowPage = nowPage;//현재 페이지
		this.totalCount = totalCount;//총 게시물 수
		this.pagePerBlock = pagePerBlock;// 한 불럭당 보여질 페이지 수
		this.numPerPage = numPerPage;//페이지당 보여질 게시물
		
		isPrePAge = false;
		isNextPage = false;
		
		//입력된 전체 게시물의 수를 통해 전체 페이지 값을 구한다.
		totalPage = (int)Math.ceil((double)totalCount/numPerPage);
		
		//현재 페이지 값이 전체페이지의 값보다 크면 전체페이지 값으로  저장
		
		if(nowPage>totalPage) {
			nowPage = totalPage;
		}
		//현재 블럭의 시작 페이지 값과 마지막 페이지 값을 구하자
		startPage =(int)((nowPage-1)/pagePerBlock)*pagePerBlock+1;
		endPage = startPage + pagePerBlock -1;
		
		//마지막 페이지가 전체페이지 값 보다 크다면
		//마지막 페이지 값을 변경
		if(endPage>totalPage)
			endPage =totalPage;
		
		//현재 페이지 값에 의해 시작게시물의 행번호와 마지막 게시물의 행 번호를 지정하여
		// 현재 페이지에 보여질 게시물 목록을 얻을 준비를 하자
		
		begin =(nowPage-1)*numPerPage+1;
		end= nowPage *numPerPage;
		
		//이전 기능 가능여부 확인
		if(startPage>1)
			isPrePAge=true;
		
		if (endPage<totalPage) 
			isNextPage = true;
			
		if(bname.equals("gocamping")){

			sb= new StringBuffer("<ol class='paging'>");
			if(isPrePAge) {
				sb.append("<li><a href='"+bname+"?cPage=");
				sb.append(nowPage-pagePerBlock);
				sb.append("&region="+catagory+"&category="+text);
				sb.append("'>&lt;</a><li>");
			}else
				sb.append("<li class='disable'>&lt;</li>");
		
					//페이지 번호를 출력하는 반복문
					for(int i= startPage; i<=endPage;i++) {
						
						if(i == nowPage) {
							sb.append("<li class='now'>");
							sb.append(i);
							sb.append("</li>");				
						}else {

							sb.append("<li><a href='"+bname+"?cPage=");
							sb.append(i);
							sb.append("&region="+catagory+"&category="+text);
							sb.append("'>");
							sb.append(i);
							sb.append("</a><li>");
						}
					}//반복문
					//다음 기능 가능여부 확인
					if(isNextPage) {
						sb.append("<li><a href='"+bname+"?cPage=");
						sb.append(nowPage+pagePerBlock);
						sb.append("&region="+catagory+"&category="+text);
						sb.append("'>&gt;</a><li>");//
					}else
						sb.append("<li class='disable'>&gt;</li>");
					
					sb.append("</ol>");
			



		}else if(catagory==null && text == null){

			sb= new StringBuffer("<ol class='paging'>");
			if(isPrePAge) {
				sb.append("<li><a href='"+bname+"?cPage=");
				sb.append(nowPage-pagePerBlock);
				sb.append("'>&lt;</a><li>");
			}else
				sb.append("<li class='disable'>&lt;</li>");
		
					//페이지 번호를 출력하는 반복문
					for(int i= startPage; i<=endPage;i++) {
						
						if(i == nowPage) {
							sb.append("<li class='now'>");
							sb.append(i);
							sb.append("</li>");				
						}else {

							sb.append("<li><a href='"+bname+"?cPage=");
							sb.append(i);
							sb.append("'>");
							sb.append(i);
							sb.append("</a><li>");
						}
					}//반복문
					//다음 기능 가능여부 확인
					if(isNextPage) {
						sb.append("<li><a href='"+bname+"?cPage=");
						sb.append(nowPage+pagePerBlock);
						sb.append("'>&gt;</a><li>");//
					}else
						sb.append("<li class='disable'>&gt;</li>");
					
					sb.append("</ol>");
				}else{	
					sb= new StringBuffer("<ol class='paging'>");
					if(isPrePAge) {
						sb.append("<li><a href='"+bname+"?cPage=");
						sb.append(nowPage-pagePerBlock);
						sb.append("&category="+catagory+"&text="+text);
						sb.append("'>&lt;</a><li>");
					}else
						sb.append("<li class='disable'>&lt;</li>");
				
					//페이지 번호를 출력하는 반복문
					for(int i= startPage; i<=endPage;i++) {
						
						if(i == nowPage) {
							sb.append("<li class='now'>");
							sb.append(i);
							sb.append("</li>");				
						}else {

							sb.append("<li><a href='"+bname+"?cPage=");
							sb.append(i);
							sb.append("&category="+catagory+"&text="+text);
							sb.append("'>");
							sb.append(i);
							sb.append("</a><li>");
						}
					}//반복문
					//다음 기능 가능여부 확인
					if(isNextPage) {
						sb.append("<li><a href='"+bname+"?cPage=");
						sb.append(nowPage+pagePerBlock);
						sb.append("&category="+catagory+"&text="+text);
						sb.append("'>&gt;</a><li>");//
					}else
						sb.append("<li class='disable'>&gt;</li>");
					
					sb.append("</ol>");
		}
	}
	

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
		//현재 페이지 값이 변경될 때 표현할 게시물들이 변경되어야 한다.
		//즉, begin,end값이 변경되어야 함
		
		//현재페이지(nowPage) 값이 총 페이지 값(totalPage)을
		// 넘지 못하게 하자!
		if(nowPage > totalPage)
			nowPage = totalPage;
		
		// 각 페이지의 시작과 끝(begin, end)지정한다.
		//   현재페이지가 1: begin:1, end: 10
		//   현재페이지가 2: begin:11, end: 20
		//   현재페이지가 3: begin:21, end: 30
		//   현재페이지가 4: begin:31, end: 40
		//   현재페이지가 5: begin:41, end: 50

		begin = (nowPage-1)*numPerPage+1;
		end = nowPage*numPerPage;
		
		//현재페이지 값에 의해 블럭의 시작페이지 값 구하기
		startPage = ((nowPage-1)/pagePerBlock)*pagePerBlock+1;
		
		//블록의 마지막 페이지 값 구하기
		endPage = startPage + pagePerBlock - 1;
		
		if(endPage > totalPage)
			endPage = totalPage;
		
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {//총 게시물 수가 변경될 때
		this.totalCount = totalCount;
		
		//총 게시물 수가 변경되면 총 페이지 수도 변경되어야 한다.
		
		totalPage = (int)Math.ceil((double)totalCount/numPerPage);
		
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public StringBuffer getSb() {
		return sb;
	}


}
