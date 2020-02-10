package com.ego.service;

import com.ego.result.BaseResult;

/**
 * Created by jick on 2019/4/15.
 * 邮件发送
 */
public interface SendMailServiceI {
    BaseResult   sendMail(String receiveMail,String  receiveName,String  html);
}
