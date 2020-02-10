package com.ego.controller;

import com.ego.pojo.Admin;
import com.ego.result.BaseResult;
import com.ego.result.BaseResultEnum;
import com.ego.service.CookieServiceI;
import com.ego.service.SSOServiceI;
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

    /*
    * serTicket	userTicket:1e9f828dae1b403da25757d9451ad852(cook的值 name为setTicket  value为缓存数据库中的value)
    * */

    /**
     * 用户登录
     *
     * @param admin
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public   BaseResult login(Admin admin, HttpServletRequest request,
                              HttpServletResponse response,String vertify){
        BaseResult  baseResult  = new BaseResult();
       //获取验证码
        String    capText =  (String)request.getSession().getAttribute("pictureVerifyKey");

        //  验证码是否一致，不一致返回提示信息
        if(!(null!=vertify && !"".equals(vertify.trim())&&vertify.trim().equals(capText))){
            baseResult.setCode(BaseResultEnum.PASS_ERROR_03.getCode());
            baseResult.setMessage(BaseResultEnum.PASS_ERROR_03.getMessage());
            return   baseResult;
        }

        //验证码一致，登录并生成票据


        //调用登陆函数，获取票据
        String  ticket = ssoService.login(admin);

        //如果票据不为空，就将它存入到cook中
        if(null !=ticket && ticket.length()>0){
             boolean flag= cookieService.setCookie(request,response,ticket);

             return   flag ?  BaseResult.success() :  BaseResult.error();
        }
        //  用户名或者密码错误
        baseResult.setCode(BaseResultEnum.PASS_ERROR_04.getCode());
        baseResult.setMessage(BaseResultEnum.PASS_ERROR_04.getMessage());
        return baseResult;


    }




    /*
    用户安全退出操作
    1、获取到ticket值
    2、根据键值执行删除cook操作
    3、最后跳到登陆界面
     */

    @RequestMapping("/logout")
    public  String    logout(HttpServletRequest request,HttpServletResponse response){
          //获取ticket值
        String   ticket  =   cookieService.getCookie(request);
        System.out.println(ticket);
        ssoService.logout(ticket);

        //清楚session
        request.getSession().removeAttribute("userTicket");

        //清除cookie
        cookieService.delCookie(request,response);
        return   "login";

    }




}