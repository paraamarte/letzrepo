package com.letz.utils.common.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.sivillage.cmcd.mapper.CodeUtilMapper;
import com.sivillage.cmcd.vo.CommonCodeSC;
import com.sivillage.cmcd.vo.CommonCodeVO;
import com.sivillage.common.constant.Constants;

/**
 * @Class Name : CommonUtils.java
 * @Description : �옄�꽭�븳 �겢�옒�뒪 �꽕紐�
 * @author UZEN /
 * @since 2016. 4. 25.
 * @version 1.0
 * @see Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */

@Component
public class CommonUtils {
    private static Logger log = LoggerFactory.getLogger(CommonUtils.class);

    private static CodeUtilMapper codeUtilMapper;

    @Autowired(required = true)
    public void setCodeUtilMapper(CodeUtilMapper mapper) {
        codeUtilMapper = mapper;
    }

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

    /**
     * <h3>�옖�뜡 �닽�옄 �깮�꽦</h3>
     *
     * @param no
     *            �옖�뜡�닽�옄 �옄由ъ닔
     * @param flag
     *            �옖�뜡�닽�옄�쓽 以묐났�뿬遺�(true : 以묐났�뿀�슜, false : 以묐났�뿀�슜�븯吏� �븡�쓬)
     * @return 吏��젙�븳 湲몄씠留뚰겮�쓽 臾몄옄�뿴
     */

    public static String getRandomNo(int no, Boolean flag) {
        String randNo = "";
        int idx = 0;
        int[] randArr = new int[no];

        // 諛곗뿴 珥덇린�솕
        for (int i = 0; i < randArr.length; i++) {
            randArr[i] = 0;
        }

        while (idx < no) {
            boolean gbn = false;

            double ranNo = Math.random() * 10;
            int floorRanNo = (int) Math.floor(ranNo);

            if (flag == false) { // 以묐났 �뿀�슜 �븞�뻽�쓣寃쎌슦
                for (int i = 0; i <= idx; i++) {
                    log.debug("randArr[" + i + "] " + randArr[i]);
                    log.debug("floorRanNo " + floorRanNo);
                    if (randArr[i] == floorRanNo) {
                        gbn = true;
                        break;
                    }
                }
            }

            if (gbn == false) {
                randArr[idx] = floorRanNo;
                log.debug("real randArr[" + idx + "] " + randArr[idx]);
                idx++;
                gbn = false;
            }
        }

        // 諛곗뿴�쓽 �닽�옄瑜� �븯�굹�쓽 臾몄옄�뿴濡� 留뚮뱾�뼱 由ы꽩�븿
        for (int j = 0; j < randArr.length; j++) {
            randNo = randNo + randArr[j];
        }

        log.debug("randNo------------------------>" + randNo);

        return randNo;
    }

    /**
     * <pre>
     * profile �엯�젰媛� �냼�닔媛믪씠 議댁옱�븯�뒗吏� �솗�씤 (ex 1.0 = false, 1.1 = true)
     * </pre>
     * 
     * @return
     */
    public static boolean scanNumber(float scanNumner) {
        boolean rtnVal;
        float fVal;
        // �냼�닔遺�留� 異붿텧
        fVal = scanNumner - (int) scanNumner;

        if (fVal == 0) {
            rtnVal = false;
        } else {
            rtnVal = true;
        }
        return rtnVal;
    }

    /**
     * 釉뚮옖�뱶 由ъ썙�뱶 �씠踰ㅽ듃�슜 釉뚮옖�뱶 由ъ뒪�듃 Get(today)
     * 
     * @return
     */
    public static List<String> getEventBrandList() {

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(dt).toString();
        sdf = new SimpleDateFormat("HH");
        String hour = sdf.format(dt).toString();
        if (Integer.parseInt(hour) < 10) {
            today = String.valueOf(new Long(today) - 1L);
        }
        return getEventBrandList(today);
    }

    /**
     * 釉뚮옖�뱶 由ъ썙�뱶 �씠踰ㅽ듃�슜 釉뚮옖�뱶 由ъ뒪�듃 Get(�듅�젙�씪)
     * 
     * @return
     */
    public static List<String> getEventBrandList(String inDate) {
        CommonCodeSC commonCodeSC = new CommonCodeSC();
        commonCodeSC.setSearchUseYn("Y");
        commonCodeSC.setSearchGrpCd("CM045");
        List<CommonCodeVO> commonCodeVOList = codeUtilMapper.getCommonCodeList(commonCodeSC);

        List<String> rtnList = new ArrayList<String>();

        for (CommonCodeVO commonCodeVO : commonCodeVOList) {
            String date = commonCodeVO.getOpt2();
            String brndCd = commonCodeVO.getOpt1();
            if (date.equals(inDate)) {
                rtnList.add(brndCd);
            }
        }
        return rtnList;
    }

    /**
     * @Desc : 湲고쉷�쟾/�씠踰ㅽ듃 APP 梨꾨꼸 異붽��릺�뿀湲곗뿉 湲곗〈 Mobile濡� �뀑�똿�븯�뜕 controller�뿉�꽌 �빐�떦 method�샇異쒗븯�뿬 App寃쎌슦 App 梨꾨꼸 �뵆�옒洹� �뀑�똿�븯�룄濡� 怨듯넻 util �깮�꽦
     * @since : 2017. 1. 4.
     * @author : uzen_FIC02571
     * @param request
     * @return
     */
    public static String getConChannel(HttpServletRequest request) {
        String conChannel = Constants.ACCESS_POINT_CHANNEL_MOBILE;
        try {
            if (StringUtils.isNotBlank((SessionUtil.getAttribute(Constants.MOBILE_USER_AGENT_SESSION_ID)).toString())) {
                conChannel = Constants.ACCESS_POINT_CHANNEL_APP;
            }
        } catch (Exception e) {

        }
        return conChannel;
    }

    public static void main(String[] args) {
        List<String> brndList = getEventBrandList();
        for (String brnd : brndList) {
            log.debug("brnd = " + brnd);
        }
    }
}
