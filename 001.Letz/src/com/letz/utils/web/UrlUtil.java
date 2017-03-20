package com.letz.utils.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivillage.utils.message.MessageUtil;

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
        // Exception 諛쒖깮 �떆 originalUrl return�븯湲� �쐞�빐 �뀑�똿
        String resultUrl = originalUrl;

        resultUrl = getShortenUrlGoogle(originalUrl);

        return resultUrl;
    }

    /**
     * @Desc : google api瑜� �궗�슜�븯�뿬 shortenUrl return �븿.
     * @since : 2016. 6. 2.
     * @author : uzen_letz
     * @param originalUrl
     * @return
     */
    public static String getShortenUrlGoogle(String originalUrl) {
        String originalUrlJsonStr = "{\"longUrl\":\"" + originalUrl + "\"}";
        logger.debug("[DEBUG] INPUT_JSON : " + originalUrlJsonStr);

        // Google�뿉 蹂��솚 �슂泥��쓣 蹂대궡湲곗쐞�빐 java.net.URL, java.net.HttpURLConnection �궗�슜
        URL url = null;
        HttpURLConnection connection = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        StringBuilder sb = null;
        JSONObject jsonObj = null;

        String SHORTENER_URL = MessageUtil.getMessage("config.google.shortenUrl.apiUrl");
        String API_KEY = MessageUtil.getMessage("config.google.shortenUrl.appKey");

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
            // Google �떒異뷫RL �꽌鍮꾩뒪 �슂泥�
            osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(originalUrlJsonStr);
            osw.flush();

            // BufferedReader�뿉 Google�뿉�꽌 諛쏆� �뜲�씠�꽣瑜� �꽔�뼱以�
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
}