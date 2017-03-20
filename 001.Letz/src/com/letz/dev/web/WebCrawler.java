package com.letz.dev.web;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class WebCrawler {

  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
      int pageNum = 1;
      String urlStr =String.format("http://devyongsik.tistory.com/category/%s?page=%d", URLEncoder.encode("책소개", "UTF-8"),pageNum);
      System.out.println(urlStr);
      HttpPost http = new HttpPost(urlStr);
      HttpClient httpClient = HttpClientBuilder.create().build();
      HttpResponse response = httpClient.execute(http);
      HttpEntity entity = response.getEntity();
      ContentType contentType = ContentType.getOrDefault(entity);

      Charset charset = contentType.getCharset();

      BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
      StringBuffer sb = new StringBuffer();

      String line = "";

      while((line=br.readLine()) != null){

          sb.append(line+"\n");

      }


      System.out.println(sb.toString());

      Document doc = Jsoup.parse(sb.toString());

      Document doc2 = Jsoup.connect(urlStr).get();

    System.out.println(doc2.data());


  }
}
