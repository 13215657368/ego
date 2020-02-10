package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * 公共页面跳转 restful风格
     * 比如：前台传welcome就跳转至/WEB-INF/pages/welcome.jsp
     * 比如：前台传login就跳转至/WEB-INF/pages/login.jsp
     * ...
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        System.out.println("pageController-----------------" + page);
        return page;
    }

    /**
     * 精确匹配login请求
     */
    @RequestMapping("/login")
    public String login(String redirectUrl, Model model) {
        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }

}
