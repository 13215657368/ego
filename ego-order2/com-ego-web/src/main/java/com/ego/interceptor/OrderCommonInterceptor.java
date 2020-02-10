package com.ego.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 */
@Component("orderCommonInterceptor")
public class OrderCommonInterceptor implements HandlerInterceptor {

    @Value("${ego.portal.url}")
    private String portalUrl;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 获取ServletContext
        ServletContext context = httpServletRequest.getSession().getServletContext();
        // 判断ServletContext作用域中是否存在orderUrl
        String url = (String) context.getAttribute("portalUrl");
        if (null == url || url.length() <= 0)
            context.setAttribute("portalUrl", portalUrl);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
