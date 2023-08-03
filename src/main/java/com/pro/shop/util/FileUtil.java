package com.pro.shop.util;

import java.io.File;

public class FileUtil {

    public static String checkSameFileName(String fileName, String path) {
		// 인자인 fileName에서 확장자를 뺀 파일명을 가려내자!
		// 우선 "."의 위치를 알아내자
		int period = fileName.lastIndexOf("."); // test1234.txt ----> 8
		
		String f_name = fileName.substring(0, period); // test1234
		String suffix = fileName.substring(period); // .txt
		
		//전체경로
		String saveFileName = path + System.getProperty("file.separator") + fileName;

        System.out.println("전체경로 : "+saveFileName);
		
		File f = new File(saveFileName);
		
		// 같은 이름이 있을 경우 파일명 뒤에 숫자를 붙여준다. 그래서 숫자를 하나 준비하자
		int idx = 1;
		while(f != null && f.exists()) {
			//파일이 이미 존재하므로 이름을 변경하자!
			StringBuffer sb = new StringBuffer();
			sb.append(f_name);
			sb.append(idx++);// 변경된 이름이 있을 수도 있으므로 idx값을 이단 붙여주고 1 증가시킴!
			sb.append(suffix);
			
			fileName = sb.toString();
			saveFileName = path + System.getProperty("file.separator") + fileName;
			
			f = new File(saveFileName);
			
		}//while문의 끝
		return fileName;
	}
	
}
