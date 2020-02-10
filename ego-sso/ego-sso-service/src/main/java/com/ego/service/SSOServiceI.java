package com.ego.service;

import com.ego.pojo.Admin;

public interface SSOServiceI {
    /**
     * 登录认证方法返回票据 ticket
     *
     * @param admin
     * @return
     */
    String login(Admin admin);

    /**
     * 验证票据 ticket 返回用户信息
     *
     * @param ticket
     * @return
     */
    Admin validate(String ticket);
    /*
    1、系统的安全退出功能实现
    */
    void  logout(String ticket);
}