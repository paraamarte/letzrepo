package com.letz.dev.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class WebGathering {

  public static void main(String[] args) {
//    // TODO Auto-generated method stub
    int pageNum = 1;
//    try {
//      System.out.println(urlStr);
//      URL url = new URL(urlStr );
//      BufferedReader in = new BufferedReader(new InputStreamR(url.openStream()));
//      System.out.println(in.readLine());
//    } catch (Exception e) { // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
      URL u;
      InputStream is = null;
      DataInputStream dis;
      String s;

      try
      {
        String urlStr =String.format("http://devyongsik.tistory.com/category/%s?page=%d", URLEncoder.encode("책소개", "UTF-8"),pageNum);
        u = new URL(urlStr);
        is = u.openStream();
        dis = new DataInputStream(new BufferedInputStream(is));
        while ((s = dis.readLine()) != null)
        {
          System.out.println(s);
        }
      }
      catch (Exception mue)
      {
        System.err.println("Ouch - a MalformedURLException happened.");
        mue.printStackTrace();
        System.exit(2);
      }
  }
}
