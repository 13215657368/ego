package com.ego.interceptor;

import com.ego.pojo.Admin;
import com.ego.service.SSOServiceI;
import com.ego.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("portalLoginInterceptor")
public class PortalLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SSOServiceI ssoService;
    @Value("${user.ticket}")
    private String userTicket;

    //  请求处理的方法之前执行
//  如果返回 true ，执行下一个拦截器或目标程序，如果返回 false ，不执行下一个拦截器或目标程序
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o)
            throws Exception {
//  获取用户名票据
        String ticket = CookieUtil.getCookieValue(httpServletRequest, userTicket);
        if (null != ticket && ticket.length() > 0) {
//  票据存在进行验证
            Admin admin = ssoService.validate(ticket);
            if (null != admin) {
//  将用户信息，添加至 session 中，用于页面返显
                httpServletRequest.getSession().setAttribute("user", admin);
                return true;
            } else {
                //如果消息为空清除以前的session信息
                httpServletRequest.getSession().removeAttribute("user");
                return true;
            }
        }
        //清除session信息
        httpServletRequest.getSession().removeAttribute("user");
        return true;

    }


    //  请求处理的方法之后执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {
    }

    // DispatcherServlet 处理后执行 -- 清理工作
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, Exception e)
            throws Exception {
    }
}