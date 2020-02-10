package com.ego.service.impl;

import com.ego.service.CookieServiceI;
import com.ego.util.CookieUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieServiceImpl implements CookieServiceI {

    @Value("${user.ticket}")
    private String userTicket;

    /**
     * 设置cookie
     *
     * @param request
     * @param response
     * @param cookieValue
     * @return
     */
    @Override
    public boolean setCookie(HttpServletRequest request, HttpServletResponse response,
                             String cookieValue) {
        CookieUtil.setCookie(request, response, userTicket, cookieValue);
        return true;
    }

    /**
     * 获取cookie
     *
     * @param request
     * @return
     */
    @Override
    public String getCookie(HttpServletRequest request) {
        return CookieUtil.getCookieValue(request, userTicket);
    }

    /**
     * 删除cookie
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response, userTicket);
        return true;
    }

}
