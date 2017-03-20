package com.letz.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ShortenUrlGoogle {

    // Google 단축URL 사용을 위한 URL
    public static final String SHORTENER_URL = "https://www.googleapis.com/urlshortener/v1/url?key=";

    public static final String API_KEY = "AIzaSyDuZFUmoWdXQ3NaraydWsGJkta2VNYKxz4"; // 새로운 키 등록 필요

    // #######################################################################################
    // 단축시킬 URL 주소를 String 문자열로 입력받고, Google API에 전송 (JSON 첨부)
    // 결과 JSON String 데이터를 수신하여, JSONObject변환
    // JSONObject 에서 단축URL을 String 타입으로 return
    // 인증키당 일 100,000 변환 가능
    // #######################################################################################
    public static String getShortenUrl(String originalUrl) {

        System.out.println("[DEBUG] INPUT_URL : " + originalUrl);

        // Exception에 대비해 결과 URL은 처음에 입력 URL로 셋팅
        String resultUrl = originalUrl;

        // Google Shorten URL API는 JSON으로 longUrl 파라미터를 사용하므로, JSON String 데이터 생성
        String originalUrlJsonStr = "{\"longUrl\":\"" + originalUrl + "\"}";
        System.out.println("[DEBUG] INPUT_JSON : " + originalUrlJsonStr);

        // Google에 변환 요청을 보내기위해 java.net.URL, java.net.HttpURLConnection 사용
        URL url = null;
        HttpURLConnection connection = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        StringBuffer sb = null; // Google의 단축URL서비스 결과 JSON String Data
        JSONObject jsonObj = null; // 결과 JSON String Data로 생성할 JSON Object

        // Google 단축 URL 요청을 위한 주소 - https://www.googleapis.com/urlshortener/v1/url
        // get방식으로 key(사용자키) 파라미터와, JSON 데이터로 longUrl(단축시킬 원본 URL이 담긴 JSON 데이터) 를 셋팅하여 전송
        try {
            url = new URL(SHORTENER_URL + API_KEY);
            System.out.println("[DEBUG] DESTINATION_URL : " + url.toString());

        } catch (Exception e) {
            System.out.println("[ERROR] URL set Failed");
            e.printStackTrace();
            return resultUrl;
        }

        // 지정된 URL로 연결 설정
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "toolbar");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
        } catch (Exception e) {
            System.out.println("[ERROR] Connection open Failed");
            e.printStackTrace();
            return resultUrl;
        }

        // 결과 JSON String 데이터를 StringBuffer에 저장
        try {
            // Google 단축URL 서비스 요청
            osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(originalUrlJsonStr);
            osw.flush();

            // BufferedReader에 Google에서 받은 데이터를 넣어줌
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            // BufferedReader 내 데이터 StringBuffer sb 에 저장
            sb = new StringBuffer();
            String buf = "";
            while ((buf = br.readLine()) != null) {
                sb.append(buf);
            }
            System.out.println("[DEBUG] RESULT_JSON_DATA : " + sb.toString());

            // Google에서 받은 JSON String을 JSONObject로 변환
            jsonObj = new JSONObject(sb.toString());

            resultUrl = jsonObj.getString("id");

        } catch (Exception e) {
            System.out.println("[ERROR] Result JSON Data(From Google) set JSONObject Failed");
            e.printStackTrace();
            return resultUrl;
        } finally {
            if (osw != null)
                try {
                    osw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (br != null)
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        System.out.println("[DEBUG] RESULT_URL : " + resultUrl);
        return resultUrl;
    }

    public static void main(String[] args) {
        String originalUrl = "https://support.google.com/cloud/answer/6158857?hl=ko";
        System.out.println("[DEBUG] main() RESULT_URL  : " + ShortenUrlGoogle.getShortenUrl(originalUrl));
    }
}
