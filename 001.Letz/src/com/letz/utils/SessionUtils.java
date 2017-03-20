package com.letz.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sivillage.core.vo.UserInfo;
import com.sivillage.utils.message.MessageUtil;

/**
 * @Class Name : SessionUtils.java
 * @Description : �옄�꽭�븳 �겢�옒�뒪 �꽕紐�
 * @author UZEN / BILL
 * @since 2016. 4. 28.
 * @version 1.0
 * @see Copyright(c) 2016 SHINSEGAE INTERNATIONAL. All rights reserved
 */

public class SessionUtils {

    private static final Logger log = LoggerFactory.getLogger(SessionUtils.class);

    /**
     * attribute 媛믪쓣 媛��졇 �삤湲� �쐞�븳 method
     *
     * @param String
     *            attribute key name
     * @return Object attribute obj
     */
    public static Object getAttribute(String name) {
        return (Object) RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * attribute �꽕�젙 method
     *
     * @param String
     *            attribute key name
     * @param Object
     *            attribute obj
     * @return void
     */
    public static void setAttribute(String name, Object object) {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * �꽕�젙�븳 attribute �궘�젣
     *
     * @param String
     *            attribute key name
     * @return void
     */
    public static void removeAttribute(String name) throws Exception {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 濡쒓렇�씤 �궗�슜�옄 �젙蹂� �뼸湲�
     * 
     * @param req
     * @return
     */
    public static UserInfo getLoginUserInfo(HttpServletRequest req) {
        return getLoginUserInfo();
    }

    public static UserInfo getLoginUserInfo() {
        return (UserInfo) getAttribute(MessageUtil.getMessage("config.bo.userinfo.key"));
    }

    /**
     * 濡쒓렇�씤 �궗�슜�옄 �젙蹂� �꽭�똿
     * 
     * @param req
     * @param userInfo
     */
    public static void setLoginUserInfo(HttpServletRequest req, UserInfo userInfo) {
        setLoginUserInfo(userInfo);
    }

    public static void setLoginUserInfo(UserInfo userInfo) {
        setAttribute(MessageUtil.getMessage("config.bo.userinfo.key"), userInfo);
    }

    /**
     * �꽭�뀡 vaild
     * 
     * @param req
     */
    public static void removeLoginUserInfo(HttpServletRequest req) {
        HttpSession sess = req.getSession();
        sess.invalidate();
    }

    /**
     * �굹�씠�뒪 �쑕���쟾�솕 蹂몄씤 �솗�씤 requestNumber �뼸湲�
     * 
     * @param req
     * @return
     */
    public static String getNiceHpCertRequestNumber(HttpServletRequest req) {
        return getNiceHpCertRequestNumber();
    }

    public static String getNiceHpCertRequestNumber() {
        return (String) getAttribute(MessageUtil.getMessage("config.nice.hp.cert.requestnumber.key"));
    }

    /**
     * �굹�씠�뒪 �쑕���쟾�솕 蹂몄씤 �솗�씤 requestNumber �꽭�똿
     * 
     * @param req
     * @param requestNumber
     */
    public static void setNiceHpCertRequestNumber(HttpServletRequest req, String requestNumber) {
        setNiceHpCertRequestNumber(requestNumber);
    }

    public static void setNiceHpCertRequestNumber(String requestNumber) {
        setAttribute(MessageUtil.getMessage("config.nice.hp.cert.requestnumber.key"), requestNumber);
    }

    /**
     * �굹�씠�뒪 IPIN蹂몄씤 �솗�씤 requestNumber �뼸湲�
     * 
     * @param req
     * @return
     */
    public static String getNiceIpCertCpRequest(HttpServletRequest req) {
        return getNiceIpCertCpRequest();
    }

    public static String getNiceIpCertCpRequest() {
        return (String) getAttribute(MessageUtil.getMessage("config.nice.ip.cert.cprequest.key"));
    }

    /**
     * �굹�씠�뒪 IPIN蹂몄씤 �솗�씤 requestNumber �꽭�똿
     * 
     * @param req
     * @param requestNumber
     */
    public static void setNiceIpCertCpRequest(HttpServletRequest req, String requestNumber) {
        setNiceIpCertCpRequest(requestNumber);
    }

    public static void setNiceIpCertCpRequest(String requestNumber) {
        setAttribute(MessageUtil.getMessage("config.nice.ip.cert.cprequest.key"), requestNumber);
    }

    /**
     * �쁽�옱 session id瑜� �뼸�뒗�떎
     * 
     * @param req
     */
    public static String getSessionId(HttpServletRequest req) {
        HttpSession sess = req.getSession();
        return sess.getId();
    }

    public static String getSessionId() {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
}
