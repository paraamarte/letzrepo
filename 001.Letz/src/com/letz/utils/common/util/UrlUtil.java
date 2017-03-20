package com.sivillage.common.util;

import static com.sivillage.common.constant.ConfigKeys.GOOGLE_SHORTENURL_APIURL;
import static com.sivillage.common.constant.ConfigKeys.GOOGLE_SHORTENURL_APPKEYL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : UrlUtil.java
 * @Description : url util class
 * @author UZEN / letz
 * @since 2016. 6. 2.
 * @version 1.0
 * @see
 *      Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class UrlUtil {
    private static Logger logger = LoggerFactory.getLogger(UrlUtil.class);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // getShortenUrlGoogle("https://docs.google.com/spreadsheets/d/1TGsSXhi5kYB8ZVOwyjA_wzGehdmTqHyf7wlJYp5kFEQ/edit#gid=0");
    }

    public static String getShortenUrl(String originalUrl) {
        // Exception 발생 시 originalUrl return하기 위해 셋팅
        String resultUrl = originalUrl;

        resultUrl = getShortenUrlGoogle(originalUrl);

        return resultUrl;
    }

    /**
     * @Desc : google api를 사용하여 shortenUrl return 함.
     * @since : 2016. 6. 2.
     * @author : uzen_letz
     * @param originalUrl
     * @return
     */
    public static String getShortenUrlGoogle(String originalUrl) {
        String originalUrlJsonStr = "{\"longUrl\":\"" + originalUrl + "\"}";
        logger.debug("[DEBUG] INPUT_JSON : " + originalUrlJsonStr);

        // Google에 변환 요청을 보내기위해 java.net.URL, java.net.HttpURLConnection 사용
        URL url = null;
        HttpURLConnection connection = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        StringBuilder sb = null;
        JSONObject jsonObj = null;

        String SHORTENER_URL = ConfigUtil.getString(GOOGLE_SHORTENURL_APIURL);
        String API_KEY = ConfigUtil.getString(GOOGLE_SHORTENURL_APPKEYL);

        // Url setting
        try {
            url = new URL(SHORTENER_URL + API_KEY);
            // logger.debug("[DEBUG] DESTINATION_URL : " + url.toString());
        } catch (Exception e) {
            logger.error("[ERROR] URL set Failed");
            e.printStackTrace();
            return originalUrl;
        }

        // API Connection
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "toolbar");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
        } catch (Exception e) {
            logger.error("[ERROR] Connection open Failed");
            e.printStackTrace();
            return originalUrl;
        }

        String resultUrl = "";
        try {
            // Google 단축URL 서비스 요청
            osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(originalUrlJsonStr);
            osw.flush();

            // BufferedReader에 Google에서 받은 데이터를 넣어줌
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            sb = new StringBuilder();
            String buf = "";
            while ((buf = br.readLine()) != null) {
                sb.append(buf);
            }
            // logger.debug("[DEBUG] RESULT_JSON_DATA : " + sb.toString());

            jsonObj = new JSONObject(sb.toString());
            resultUrl = jsonObj.getString("id");

        } catch (Exception e) {
            logger.error("[ERROR] Result JSON Data(From Google) set JSONObject Failed");
            e.printStackTrace();
            return originalUrl;
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
        logger.debug("[DEBUG] RESULT_URL : " + resultUrl);
        return resultUrl;
    }
    
    public static String getUriForNaverTracking(HttpServletRequest req, String outUrl) {
        String inUrl = req.getRequestURL().toString();
        String referer = req.getHeader("referer");
        String queryString = req.getQueryString();
        
        if (StringUtils.isNotEmpty(referer) && referer.contains("sivillage.com")) {
            referer = "";
        }
        
        if (StringUtils.isEmpty(outUrl)) {
            outUrl = "/intro";
        }
        
        if (StringUtils.isNotEmpty(queryString) || StringUtils.isNotEmpty(referer)) {
            outUrl += "?";
        }

        if (StringUtils.isNotEmpty(queryString)) {
            if (queryString.startsWith("&")) {
                queryString = queryString.substring(1);
            }
            
            outUrl += queryString;
        }
        
        if (StringUtils.isNotEmpty(referer)) {
            outUrl += String.format("%ssource=%s&xdr=", StringUtils.isNotEmpty(queryString) ? "&":"", StringUtils.isNotEmpty(referer) ? referer : "");
        }
        
        logger.debug("inUrl:outUrl = {}:{}", inUrl, outUrl);
        
        return outUrl;
    }
}
