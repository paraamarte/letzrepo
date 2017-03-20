package com.letz.utils;

import javax.servlet.http.Cookie;

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
}
