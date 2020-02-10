package com.ego.controller;

import com.ego.pojo.Admin;
import com.ego.pojo.AdminWithBLOBs;
import com.ego.result.BaseResult;
import com.ego.service.CookieServiceI;
import com.ego.service.SSOServiceI;
import com.ego.service.SendMailServiceI;
import com.ego.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SSOServiceI ssoService;

    @Autowired
    private CookieServiceI cookieService;

    @Autowired
    private SendMailServiceI  sendMailService;

    @Autowired
    private   UserServiceI userService;

    /**
     * 用户登录
     *
     * @param admin
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public BaseResult login(Admin admin, HttpServletRequest request, HttpServletResponse response) {
        String ticket = ssoService.login(admin);
        // 票据信息存在，设置至cookie
        if (null != ticket && ticket.length() > 0) {
            cookieService.setCookie(request, response, ticket);
            return BaseResult.success();
        }
        return BaseResult.error();
    }

    /**
     * 用户退出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 清除redis
        String ticket = cookieService.getCookie(request);
        ssoService.logout(ticket);
        // 清除sesison
        request.getSession().removeAttribute("user");
        // 清除cookie
        cookieService.deleteCookie(request, response);
        return "index";
    }


    /**
     * 用户注册
     */

    @RequestMapping("/register")
    @ResponseBody
    public    BaseResult  register(AdminWithBLOBs  admin){
        BaseResult  result  = userService.adminSave(admin);
        //注册成功发送邮件
        if(200==result.getCode())
            result = sendMailService.sendMail(admin.getEmail(),admin.getUserName(),"registerEmail");
            return    result;
    }

}
