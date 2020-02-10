package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jick on 2019/3/25.
 */
@Controller
public class PageController {

    /**
     * 公共页面跳转  restful 风格
     * 比如：前台传 welcome 就跳转至 /WEB-INF/pages/welcome.jsp
     * 比如：前台传 login 就跳转至 /WEB-INF/pages/login.jsp
     * ...
     *
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        System.out.println("pageController-----------------" + page);
        return page;

    }

}
