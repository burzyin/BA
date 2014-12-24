package com.exadel.belarusattractions.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class contains some common used utils methods.
 *
 * Developer: Yan Khonskiy
 * Created: 12:37 PM, 2/8/13
 */

public class CommonMethodController {

    public static String getScheme(HttpServletRequest request) {
        return request.getScheme();
    }

    public static String getDomain(HttpServletRequest request) {
        String serverName = request.getServerName();
        String subDomain = serverName.substring(serverName.indexOf(".") + 1, serverName.length());
        return subDomain.substring(subDomain.indexOf(".") + 1, subDomain.length());
    }

    public static Cookie createSessionIdCookie(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String domain = CommonMethodController.getDomain(request);

        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setDomain(domain);
        return cookie;
    }
}