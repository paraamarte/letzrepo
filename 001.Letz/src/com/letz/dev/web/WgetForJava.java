package com.letz.dev.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URLEncoder;

public class WgetForJava {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int pageNum = 0;
    try {
        for(pageNum=1;pageNum <=8;pageNum++){
          String urlStr =
              String.format(
                  "wget -O page_%d http://devyongsik.tistory.com/category/%s?page=%d",
                  pageNum, URLEncoder.encode("책소개", "UTF-8"), pageNum);
          
         Runtime.getRuntime().exec(urlStr);
         System.out.println(String.format("%d번째 page 다운로드 완료!", pageNum) ); 
         System.out.println(String.format("%d번째 file open!", pageNum) );
         BufferedReader in = new BufferedReader(new FileReader("page_"+pageNum));
         String s;

         while ((s = in.readLine()) != null) {
//             if(s.indexOf("<div class=\"titleWrap\">") > 0)
//                 System.out.println(s.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
                 System.out.println(s);
         }
         in.close();
        }
      } catch (Exception e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
