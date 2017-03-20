package com.letz.utils;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxy {
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * HTTP request
     * 
     * @param url
     *            �슂泥��븷 url
     * @return �꽌踰� �쓳�떟寃곌낵 臾몄옄�뿴
     */
    private String requestHTTP(String getPost, String url) throws Exception {
        // URI �꽕�젙
        URI uri = new URI(url);

        // HTTP �슂泥�
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        if ("GET".equals(getPost)) { // GET
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Accept", "application/json");
            response = httpClient.execute(httpGet);
        } else { // POST
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Accept", "application/json");
            response = httpClient.execute(httpPost);

        }

        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity); // �쓳�떟
        System.out.println("content: " + content);

        return content;
    }

    public String post(String url) throws Exception {
        return requestHTTP("POST", url);
    }

    public String get(String url) throws Exception {
        return requestHTTP("GET", url);
    }

    private List<NameValuePair> convertParam(Map<String, String> params, String encoding) throws Exception {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        Iterator<String> keys = params.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            paramList.add(new BasicNameValuePair(key, URLEncoder.encode(params.get(key).toString(), encoding)));
        }

        return paramList;
    }

    public static void main(String[] args) {
        HttpProxy p = new HttpProxy();

        try {
            p.post("http://localhost:8070/common/refreshGlobalCodeList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
