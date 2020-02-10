package com.ego.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieServiceI {
    /**
     * 设置 Cookie
     *
     * @param request
     * @param response
     * @param ticket
     * @return
     */
    boolean setCookie(HttpServletRequest request, HttpServletResponse
            response, String ticket);


    /*
    删除cook操作
     */
    void  delCookie(HttpServletRequest request, HttpServletResponse response);



    //获取cookie中的ticket值
    String   getCookie(HttpServletRequest request);
}