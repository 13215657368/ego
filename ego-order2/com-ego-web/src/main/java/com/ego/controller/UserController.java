package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        return "redirect:"
                + request.getSession().getServletContext().getAttribute("portalUrl")
                + "user/logout";
    }

}
