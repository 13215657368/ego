package com.ego.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie service
 */
public interface CookieServiceI {

    /**
     * 设置cookie
     *
     * @param request
     * @param response
     * @param cookieValue
     * @return
     */
    boolean setCookie(HttpServletRequest request, HttpServletResponse response, String cookieValue);

    /**
     * 获取cookie
     *
     * @param request
     * @return
     */
    String getCookie(HttpServletRequest request);

    /**
     * 删除cookie
     *
     * @param request
     * @param response
     * @return
     */
    boolean deleteCookie(HttpServletRequest request, HttpServletResponse response);

}
