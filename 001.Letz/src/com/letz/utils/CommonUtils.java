/*
 * This software is the confidential and proprietary information of
 * Shinsegae Internatinal Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Shinsegae International.
 */
package com.letz.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;

import com.sivillage.utils.message.MessageUtil;

/**
 * @Class Name : CommonUtils.java
 * @Description : common util
 * @author UZEN /
 * @since 2015. 10. 7.
 * @version 1.0
 * @see
 *      Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */
public class CommonUtils {
    public static Model makeGridData(Model model, List<?> list, String total) {
        if (list != null) {
            model.addAttribute("total", total);
            model.addAttribute("records", list);
            model.addAttribute("satus", "success");
        } else {
            model.addAttribute("total", "0");
            model.addAttribute("records", list);
            model.addAttribute("satus", "error");
        }
        return model;
    }

    /**
     * MD5 �씤肄붾뵫
     * 
     * @param baseString
     * @return
     */
    public static String encMd5(String baseString) {
        String resultString = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(baseString.getBytes());
            for (int i = 0; i < digest.length; i++) {
                resultString = resultString + Integer.toHexString(digest[i] & 0xFF);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * SHA-1 �씤肄붾뵫
     * 
     * @param baseString
     * @return
     */
    public static String encSha1(String baseString) {
        String resultString = "";
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] digest = sha1.digest(baseString.getBytes());
            for (int i = 0; i < digest.length; i++) {
                resultString = resultString + Integer.toHexString(digest[i] & 0xFF);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * SAH256 �씤肄붾뵫
     * 
     * @param baseString
     * @return
     */
    public static String encSha256(String baseString) {
        String resultString = "";
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha256.digest(baseString.getBytes());
            for (int i = 0; i < digest.length; i++) {
                resultString = resultString + Integer.toHexString(digest[i] & 0xFF);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * Hmac sha1 �씤肄붾뵫
     * 
     * @param keyString
     * @param baseString
     * @return
     */
    public static String encMacSha1(String keyString, String baseString) {
        String resultString = "";
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(key);
            byte[] bytes = mac.doFinal(baseString.getBytes("UTF-8"));
            // String resultString = new String(Base64.encodeBase64(bytes));
            for (int i = 0; i < bytes.length; i++) {
                resultString = resultString + Integer.toHexString(bytes[i] & 0xFF);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    /**
     * �젒�냽�옄 IP �뼸湲�
     * 
     * @param request
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 鍮꾨�踰덊샇 encoding
     * 
     * @param baseString
     * @return
     */
    public static String encPasswordString(String baseString) {
        String resultString = encMd5(baseString);
        resultString = encSha256(resultString);
        return resultString;
    }

    /**
     * list媛� null�씠嫄곕굹 size媛� 0�씠硫� true
     * 
     * @param list
     * @return
     */
    public static boolean isEmptyList(List<?> list) {
        if (list == null) {
            return true;
        }
        return list.isEmpty();
    }

    /**
     * �몢 �궇吏쒖쓽 李⑥씠
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int getDiffByDay(String str1, String str2) {
        int diffInDays = 0;
        try {
            Calendar calendar1;
            Calendar calendar2;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            Date date1 = sdf.parse(str1);
            calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);

            Date date2 = sdf.parse(str2);
            calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);

            diffInDays = (int) ((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 3600 * 24));
        } catch (java.text.ParseException e) {
            System.err.println("ParseException ");
        }
        return diffInDays;
    }

    /**
     * <h3>���긽臾몄옄�뿴�쓽 �쇊履쎌뿉 吏��젙�븳 ��泥대Ц�옄瑜� 泥댁썙�꽌 吏��젙�븳 湲몄씠�쓽 臾몄옄�뿴�쓣 諛섑솚�븳�떎.</h3>
     * <p>
     * �뿬湲곗꽌 湲몄씠�뒗 {@link java.lang.String#length()} 留ㅼ냼�뱶瑜� �궗�슜�븯�뿬 泥섎━�맂�떎.
     * </p>
     *
     * @param str
     *            ���긽臾몄옄�뿴
     * @param len
     *            湲몄씠
     * @param addStr
     *            ��泥대Ц�옄
     * @return 吏��젙�븳 湲몄씠留뚰겮�쓽 臾몄옄�뿴
     * @see #lpadByte(String, int, String)
     */

    public static String lpad(String str, int len, String addStr) {
        if (str == null) {
            str = "";
        }
        String result = str;
        int templen = len - result.length();

        for (int i = 0; i < templen; i++) {
            result = addStr + result;
        }

        return result;
    }

    /**
     * <h3>移댄뀒怨좊━ 援щ텇 媛믪뿉 �뵲�씪, �빐�떦 �꽌釉� �룄硫붿씤�쓽 �긽�뭹 �긽�꽭 url瑜� 諛섑솚�븳�떎.</h3>
     * 
     * <pre>
     * �꽌釉� �룄硫붿씤�� config�뿉�꽌 愿�由ы븳�떎.
     * 紐낆떆�릺吏� �븡�� 移댄뀒怨좊━ 援щ텇 媛믪쓣 �쟾�떖 諛쏆쑝硫� www.sivillage.com �룄硫붿씤�뿉 �긽�뭹 �긽�꽭 url�쓣 援ъ꽦�븯�뿬 諛섑솚�븳�떎.
     * </pre>
     * 
     * @param productNo
     *            �긽�뭹踰덊샇
     * @param catSct
     *            移댄뀒怨좊━ 援щ텇 (PD_SKU / ERP�뿉�꽌 �꽆寃⑤컺�뒗 援щ텇 媛�) </br>
     *            1 (�뙣�뀡) => fashion.sivillage.com </br>
     *            2 (酉고떚) => beauty.sivillage.com </br>
     *            3 (由щ튃) => living.sivillage.com </br>
     * @return url
     *         - �긽�뭹 �긽�꽭 URL
     */
    public static String getProductDetailUrl(String productNo, String catSct) {
        String url = "";

        if (StringUtils.isNotBlank(productNo)) {
            url = MessageUtil.getMessage("config.fo.server.http.url");

            if (StringUtils.isNotBlank(catSct)) {
                if (0 < Integer.parseInt(catSct) && Integer.parseInt(catSct) < 4) {
                    url = MessageUtil.getMessage("config.fo.sub.server.http.url." + catSct);
                }
            }

            url = url + MessageUtil.getMessage("config.fo.productDetail.url") + "?productNo=" + productNo;
        }

        return url;
    }
    
    /**
     * <pre>
     * profile �꽕�젙 �젙蹂대�� ��臾몄옄濡� 諛섑솚
     * </pre>
     * 
     * @return
     */
    public static String getProfile() {
        String profile = StringUtils.defaultString(System.getenv("spring.profiles.active"), System.getProperty("spring.profiles.active"));

        if (StringUtils.isNotEmpty(profile)) {
            profile = profile.toUpperCase();
        }

        return profile;
    }
    
    public static String getABCBandIp(HttpServletRequest req) {
        String sIpAddress = req.getHeader("HTTP_X_FORWARDED_FOR");
        
        if (sIpAddress == null) {
            sIpAddress = req.getRemoteAddr();
            if (sIpAddress.indexOf(".") > -1) {
                sIpAddress = sIpAddress.substring(0, sIpAddress.lastIndexOf("."));
            }
        }
        
        return sIpAddress;
    }
}
