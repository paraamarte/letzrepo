/**
 * @Class Name : StringUtils.java
 * @Description : string util
 * @Modification Information
 *               @ * @ �닔�젙�씪 �닔�젙�옄 �닔�젙�궡�슜
 *               @ --------- --------- -------------------------------
 *               @ 2014.07.10 沅뚯쨷�뿄 理쒖큹�깮�꽦
 * @author kwon
 * @since 2014. 07.10
 * @version 1.0
 * @see
 *      Copyright(c) 2014 suhyang network Co.,Ltd. All rights reserved
 */
package com.letz.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    /**
     * 臾몄옄�뿴�씠 null �씠嫄곕굹 " "(怨듬갚) �씠嫄곕굹 "" �씪寃쎌슦 true
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().length() == 0) {
            return true;
        }
        if ("null".equals(str.trim().toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * 臾몄옄�뿴�씠 null �씠硫� true
     * 
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        return false;
    }

    /**
     * 臾몄옄�뿴�씠 null �씠嫄곕굹 " "(怨듬갚) �씠嫄곕굹 "" �씪寃쎌슦 吏��젙�븳 臾몄옄�뿴 諛섑솚
     * 
     * @param str
     * @param defaultStr
     * @return
     */
    public static String blankToStr(String str, String defaultStr) {
        if (isBlank(str)) {
            return defaultStr;
        }
        return str;
    }

    /**
     * 臾몄옄�뿴�씠 null �씠嫄곕굹 " "(怨듬갚) �씠嫄곕굹 "" �씪寃쎌슦 "" 諛섑솚
     * 
     * @param str
     * @return
     */
    public static String blankToStr(String str) {
        return blankToStr(str, "");
    }

    /**
     * 臾몄옄�뿴�씠 null �씪寃쎌슦 吏��젙�븳 臾몄옄�뿴 諛섑솚
     * 
     * @param str
     * @param defaultStr
     * @return
     */
    public static String nullToStr(String str, String defaultStr) {
        if (isNull(str)) {
            return defaultStr;
        }
        return str;
    }

    /**
     * 臾몄옄�뿴�씠 null �씪寃쎌슦 "" 諛섑솚
     * 
     * @param str
     * @return
     */
    public static String nullToStr(String str) {
        return nullToStr(str, "");
    }

    /**
     * 臾몄옄�뿴�씠 null �씠嫄곕굹 " "(怨듬갚) �씠嫄곕굹 "" �씪寃쎌슦 false
     * 
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 臾몄옄�뿴�씠 null �씪寃쎌슦 false
     * 
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    /**
     * [�꽌�뼇 �꽕�듃�썚�뒪 �삩�씪�씤 �뒪�넗�뼱 援ъ텞]臾몄옄 移섑솚 硫붿냼�뱶
     * 
     * @param paramValue
     * @param gubun
     * @return
     */
    public static String requestReplace(String paramValue, String gubun) {
        String result = "";

        if (paramValue != null) {

            paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

            paramValue = paramValue.replaceAll("\\*", "");
            paramValue = paramValue.replaceAll("\\?", "");
            paramValue = paramValue.replaceAll("\\[", "");
            paramValue = paramValue.replaceAll("\\{", "");
            paramValue = paramValue.replaceAll("\\(", "");
            paramValue = paramValue.replaceAll("\\)", "");
            paramValue = paramValue.replaceAll("\\^", "");
            paramValue = paramValue.replaceAll("\\$", "");
            paramValue = paramValue.replaceAll("'", "");
            paramValue = paramValue.replaceAll("@", "");
            paramValue = paramValue.replaceAll("%", "");
            paramValue = paramValue.replaceAll(";", "");
            paramValue = paramValue.replaceAll(":", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll("#", "");
            paramValue = paramValue.replaceAll("--", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll(",", "");

            if (gubun != "encodeData") {
                paramValue = paramValue.replaceAll("\\+", "");
                paramValue = paramValue.replaceAll("/", "");
                paramValue = paramValue.replaceAll("=", "");
            }

            result = paramValue;

        }
        return result;
    }

    /**
     * <pre>
     * string�뿉 �룷�븿�릺�뼱 �엳�뒗 whitespace瑜� 援щ텇�옄濡� �븯�뿬 list �삎�깭濡� 諛섑솚
     * </pre>
     * 
     * @param str
     * @return
     */
    public static List<String> convertStringToList(String param) {
        List<String> list = new ArrayList<String>();
        String[] strArr = param.split("\\s+");
        for (String str : strArr) {
            // System.out.println("str : [" + str + "] str trim : [" + str.trim() + "]");
            if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
                str = str.trim();
                list.add(str);
            }
        }

        return list;
    }

    /**
     * <pre>
     * string�뿉 �룷�븿�릺�뼱 �엳�뒗 臾몄옄瑜� 援щ텇�옄濡� �븯�뿬 list �삎�깭濡� 諛섑솚
     * </pre>
     * 
     * @param str
     * @return
     */
    public static List<String> convertStringToList(String param, String regex) {
        List<String> list = new ArrayList<String>();
        String[] strArr = param.split(regex);
        for (String str : strArr) {
            list.add(str);
        }

        return list;
    }

}
