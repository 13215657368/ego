package com.ego.service.impl;

import com.ego.service.CookieServiceI;
import com.ego.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieServiceImpl implements CookieServiceI {
    private static Logger logger = LoggerFactory.getLogger(CookieServiceImpl.class);
    @Value("${user.ticket}")
    private String userTicket;

    /**
     * 设置 Cookie
     *
     * @param request
     * @param response
     * @param ticket
     * @return
     */
    @Override
    public boolean setCookie(HttpServletRequest request, HttpServletResponse
            response, String ticket) {

        try {
            CookieUtil.setCookie(request,response,userTicket,ticket);
            return   true;
        }catch (Exception e){
            logger.error("Cookie  设置失败！");
        }
        return   false;




    }

    @Override
    public void  delCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request,response,userTicket);
    }

    @Override
    public String getCookie(HttpServletRequest request) {

        String   ticket  =  CookieUtil.getCookieValue(request,userTicket);
        return   ticket;
    }
}