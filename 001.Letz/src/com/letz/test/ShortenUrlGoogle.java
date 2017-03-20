package com.letz.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ShortenUrlGoogle {

    // Google ����URL ����� ���� URL
    public static final String SHORTENER_URL = "https://www.googleapis.com/urlshortener/v1/url?key=";

    public static final String API_KEY = "AIzaSyDuZFUmoWdXQ3NaraydWsGJkta2VNYKxz4"; // ���ο� Ű ��� �ʿ�

    // #######################################################################################
    // �����ų URL �ּҸ� String ���ڿ��� �Է¹ް�, Google API�� ���� (JSON ÷��)
    // ��� JSON String �����͸� �����Ͽ�, JSONObject��ȯ
    // JSONObject ���� ����URL�� String Ÿ������ return
    // ����Ű�� �� 100,000 ��ȯ ����
    // #######################################################################################
    public static String getShortenUrl(String originalUrl) {

        System.out.println("[DEBUG] INPUT_URL : " + originalUrl);

        // Exception�� ����� ��� URL�� ó���� �Է� URL�� ����
        String resultUrl = originalUrl;

        // Google Shorten URL API�� JSON���� longUrl �Ķ���͸� ����ϹǷ�, JSON String ������ ����
        String originalUrlJsonStr = "{\"longUrl\":\"" + originalUrl + "\"}";
        System.out.println("[DEBUG] INPUT_JSON : " + originalUrlJsonStr);

        // Google�� ��ȯ ��û�� ���������� java.net.URL, java.net.HttpURLConnection ���
        URL url = null;
        HttpURLConnection connection = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        StringBuffer sb = null; // Google�� ����URL���� ��� JSON String Data
        JSONObject jsonObj = null; // ��� JSON String Data�� ������ JSON Object

        // Google ���� URL ��û�� ���� �ּ� - https://www.googleapis.com/urlshortener/v1/url
        // get������� key(�����Ű) �Ķ���Ϳ�, JSON �����ͷ� longUrl(�����ų ���� URL�� ��� JSON ������) �� �����Ͽ� ����
        try {
            url = new URL(SHORTENER_URL + API_KEY);
            System.out.println("[DEBUG] DESTINATION_URL : " + url.toString());

        } catch (Exception e) {
            System.out.println("[ERROR] URL set Failed");
            e.printStackTrace();
            return resultUrl;
        }

        // ������ URL�� ���� ����
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

        // ��� JSON String �����͸� StringBuffer�� ����
        try {
            // Google ����URL ���� ��û
            osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(originalUrlJsonStr);
            osw.flush();

            // BufferedReader�� Google���� ���� �����͸� �־���
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            // BufferedReader �� ������ StringBuffer sb �� ����
            sb = new StringBuffer();
            String buf = "";
            while ((buf = br.readLine()) != null) {
                sb.append(buf);
            }
            System.out.println("[DEBUG] RESULT_JSON_DATA : " + sb.toString());

            // Google���� ���� JSON String�� JSONObject�� ��ȯ
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
