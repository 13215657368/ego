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

@Component("orderLoginInterceptor")
public class OrderLoginInterceptor implements HandlerInterceptor {

    @Value("${user.ticket}")
    private String userTicket;

    @Value("${ego.portal.url}")
    private String portalUrl;

    @Autowired
    private SSOServiceI ssoService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        // 验证票据是否存在
        String ticket = CookieUtil.getCookieValue(httpServletRequest, userTicket);
        // 如果票据不存在，请登录
        if (null != ticket && ticket.length() > 0) {
            // 如果票据存在，验证票据获取用户信息
            Admin admin = ssoService.validate(ticket);
            if (null != admin) {
                // 将用户信息设置session
                httpServletRequest.getSession().setAttribute("user", admin);
                return true;
            }
        }
        // 重定向至登录页面
        httpServletResponse.sendRedirect(portalUrl + "login?redirectUrl="
                + httpServletRequest.getRequestURL());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
