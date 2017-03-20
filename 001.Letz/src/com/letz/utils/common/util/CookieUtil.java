package com.sivillage.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class CookieUtil {
    public static String getValue(Cookie[] cookies, String cookieName) {
        String cookieValue = "";

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookieName.equals(cookies[i].getName())) {
                    cookieValue = cookies[i].getValue();
                    break;
                }
            }
        }

        return cookieValue;
    }

    public static void createCookie(HttpServletResponse response, String domain, String name, String value, int maxAge,
            String path) {
        Cookie cookie = new Cookie(name, value);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);// 이부분을 적용하면 서브 도메인간 공유 가능
        }
        cookie.setMaxAge(maxAge); // 쿠키 유지 기간(이부분이 없으면 브라우저 종료시 사라짐)
        cookie.setPath(path); // 모든 경로에서 접근 가능하도록
        response.addCookie(cookie); // 쿠키저장
    }

    public static void deleteCookie(HttpServletRequest req, HttpServletResponse res, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equals(cookies[i].getName())) {
                    cookies[i].setMaxAge(0);
                    cookies[i].setPath("/");
                    res.addCookie(cookies[i]); // 쿠키저장
                }
            }
        }
    }
}
