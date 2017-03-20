package com.sivillage.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sivillage.common.constant.ConfigKeys;
import com.sivillage.display.vo.DispInfo;
import com.sivillage.member.vo.FrontUserInfo;

/**
 * session Util
 * - Spring에서 제공하는 RequestContextHolder 를 이용하여
 * request 객체를 service까지 전달하지 않고 사용할 수 있게 해줌
 */
public class SessionUtil {
    /**
     * attribute 값을 가져 오기 위한 method
     *
     * @param String
     *            attribute key name
     * @return Object attribute obj
     */
    public static Object getAttribute(String name) throws Exception {
        return (Object) RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * attribute 설정 method
     *
     * @param String
     *            attribute key name
     * @param Object
     *            attribute obj
     * @return void
     */
    public static void setAttribute(String name, Object object) throws Exception {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 설정한 attribute 삭제
     *
     * @param String
     *            attribute key name
     * @return void
     */
    public static void removeAttribute(String name) throws Exception {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * session id
     *
     * @param void
     * @return String SessionId 값
     */
    public static String getSessionId() throws Exception {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }

    public static FrontUserInfo getFrontLoginUserInfo() {
        FrontUserInfo userInfo = null;

        try {
            userInfo = (FrontUserInfo) RequestContextHolder.getRequestAttributes().getAttribute(ConfigUtil.getString(ConfigKeys.KEY_SESSION_USERINFO), RequestAttributes.SCOPE_SESSION);
        } catch (Exception ex) {
        }

        return userInfo;
    }

    public static void setFrontLoginUserInfo(FrontUserInfo userInfo) {
        try {
            setAttribute(ConfigUtil.getString(ConfigKeys.KEY_SESSION_USERINFO), userInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static DispInfo getFrontDispInfo() {
        DispInfo dispInfo = null;

        try {
            dispInfo = (DispInfo) RequestContextHolder.getRequestAttributes().getAttribute(ConfigUtil.getString(ConfigKeys.KEY_SESSION_DSPINFO), RequestAttributes.SCOPE_SESSION);
        } catch (Exception ex) {
        }

        return dispInfo;
    }

    public static void setFrontDispInfo(DispInfo dispInfo) {
        try {
            setAttribute(ConfigUtil.getString(ConfigKeys.KEY_SESSION_DSPINFO), dispInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void removeLoginUserInfo(HttpServletRequest req) {
        HttpSession sess = req.getSession();

        if (sess != null) {
            try {
                removeAttribute(ConfigUtil.getString(ConfigKeys.KEY_SESSION_USERINFO));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            sess.invalidate();
        }
    }
}