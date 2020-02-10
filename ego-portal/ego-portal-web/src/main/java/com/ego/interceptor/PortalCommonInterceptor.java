package com.ego.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by jick on 2019/4/12.
 */
@Component("portalCommonInterceptor")
public class PortalCommonInterceptor  implements HandlerInterceptor {
    /*# ego-order 系统 url
ego.order.url=http://localhost:9092/ego-order-web/
*/
    @Value("${ego.order.url}")
    private  String orderUrl;

    //请求处理的方法之前执行
    // 如果返回 true ，执行下一个拦截器或目标程序，如果返回 false ，不执行下一个拦截器或目标程序

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //  获取 Application 对象中的 url 地址
        ServletContext context = httpServletRequest.getSession().getServletContext();
        String egoOrderUrl = (String) context.getAttribute("orderUrl");
// url 地址为空添加至 Application 对象
        if (null == egoOrderUrl || 0 >= egoOrderUrl.length()) {
            context.setAttribute("orderUrl", orderUrl);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
