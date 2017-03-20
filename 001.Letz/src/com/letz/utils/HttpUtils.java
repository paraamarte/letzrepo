/*
 * This software is the confidential and proprietary information of
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Shinsegae International.
 */
package com.letz.utils;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

 /**
 * @Class Name : HttpUtils.java
 * @Description : �옄�꽭�븳 �겢�옒�뒪 �꽕紐�
 * @author UZEN / �옉�꽦�옄紐�
 * @since 2016. 2. 18.
 * @version 1.0
 * @see
 *      Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class HttpUtils {
    
    private static MultiThreadedHttpConnectionManager CONNECTION_MANAGER;
    
    static {
        if (CONNECTION_MANAGER == null) {
            CONNECTION_MANAGER = new MultiThreadedHttpConnectionManager();
        }
    }
    
    /**
     * 
     * 
     * <pre>
     * </pre>
     * 
     * @return
     */
    private static HttpClient getHttpClient() {
        HttpClient hc = new HttpClient(CONNECTION_MANAGER);
        hc.getParams().setParameter("http.connection.timeout", 5000);
        hc.getParams().setParameter("http.socket.timeout", 5000);
        return hc;
    }
    
    /**
     * 
     * 
     * <pre>
     * </pre>
     * 
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String doGetResponse(String url) throws HttpException, IOException {
        
        GetMethod getMethod = null;
        String strResponseBody = null;

        getMethod = new GetMethod(url);
        
        int status = getHttpClient().executeMethod(getMethod);
        
        if (status == HttpStatus.SC_OK) {
            strResponseBody = getMethod.getResponseBodyAsString();
        } else {
            new HttpException("" + status);
        }
        
        return strResponseBody;
        
    }
    
    public static String doPostResponse(String url, List<NameValuePair> params) throws HttpException, IOException {
        
        PostMethod postMethod = null;
        String strResponseBody = null;

        postMethod = new PostMethod(url);
        
        if (params != null) {
            for (NameValuePair nameValuePair : params) {
                
                postMethod.addParameter(nameValuePair);
            }
        }
        
        int status = getHttpClient().executeMethod(postMethod);
        
        if (status == HttpStatus.SC_OK) {
            strResponseBody = postMethod.getResponseBodyAsString();
        } else {
            new HttpException("" + status);
        }
        
        return strResponseBody;
        
    }

}
