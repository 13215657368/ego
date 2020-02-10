package com.ego.service.impl;

import com.ego.result.BaseResult;
import com.ego.service.SendMailServiceI;
import com.ego.util.ReadHtmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by jick on 2019/4/15.
 */
@Service
public class SendMailServiceImpl implements SendMailServiceI {

    private  static Logger logger = (Logger) LoggerFactory.getLogger(SendMailServiceImpl.class);

    //发件人邮箱smtp服务器地址
    @Value("${email.host}")
    private   String    emailHost;

    //  发件人的邮箱和密码
    @Value("${email.account}")
    private String emailAccount;
    @Value("${email.password}")
    private String emailPassword;
    //  发件人昵称
    @Value("${email.account.name}")
    private String emailAccountName;


    /**
     * 发送邮件
     * @param receiveMail  收件人邮箱
     * @param receiveName   收件人名称
     * @param html          发送邮件的html模板
     * @return
     */

    @Override
    public BaseResult sendMail(String receiveMail, String receiveName, String html) {
        try {
// 1.  创建参数配置对象 ,  用于连接邮件服务器的参数配置
            Properties props = new Properties();
//  开启 debug 调试
            props.setProperty("mail.debug", "true");
//  发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
//  设置邮件服务器主机名
            props.setProperty("mail.host", emailHost);
//  发送邮件协议名称（ JavaMail 规范要求）
            props.setProperty("mail.transport.protocol", "smtp");
// 2.  根据配置创建会话对象 ,  用于和邮件服务器交互
            Session session = Session.getInstance(props);
//  设置为 debug 模式 ,  可以查看详细的发送 log
            session.setDebug(true);
// 3.  创建一封邮件
            MimeMessage message = new MimeMessage(session);
// 4. From:  发件人
            message.setFrom(new InternetAddress(emailAccount, emailAccountName, "UTF-8"));
// 5. To:  收件人（可以增加多个收件人、抄送、密送）
            message.setRecipient(MimeMessage.RecipientType.TO,
                    new InternetAddress(receiveMail, receiveName, "UTF-8"));
// 6. Subject:  邮件主题
            message.setSubject("EGO  商城帐号注册成功邮件", "UTF-8");
// 7. Content:  邮件正文（可以使用 html 标签）
//  读取 html 文件内容
            String info = ReadHtmlUtil.getMailString(html);
            System.out.println(info);
// MimeMultipart 类是一个容器类，包含 MimeBodyPart 类型的对象
            Multipart mainPart = new MimeMultipart();
//  创建一个包含 HTML 内容的 MimeBodyPart
            BodyPart body = new MimeBodyPart();
//  设置 html 内容
            body.setContent(info, "text/html;charset=utf-8");
//  将 MimeMultipart 设置为邮件内容
            mainPart.addBodyPart(body);
            message.setContent(mainPart);
// 8.  设置发件时间
            message.setSentDate(new Date());
// 9.  保存设置
            message.saveChanges();
// 10.  将该邮件保存到本地
//OutputStream out = new FileOutputStream("C://MyEmail" +UUID.randomUUID().toString() + ".eml");
//message.writeTo(out);
//out.flush();
//out.close();
// 11.  根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
// 12.  使用 邮箱账号 和 密码 连接邮件服务器
            transport.connect(emailAccount, emailPassword);
// 13.  发送邮件 ,  发到所有的收件地址
            transport.sendMessage(message, message.getAllRecipients());
// 14.  关闭连接
            transport.close();
            return BaseResult.success();
        } catch (MessagingException e) {
            logger.error(" 邮件发送失败，发件人：" + receiveMail +
                    " ，发件人名称：" + receiveName +
                    " 失败原因：" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error(" 邮件发送失败，发件人：" + receiveMail +
                    " ，发件人名称：" + receiveName +
                    " 失败原因：" + e.getMessage());
        }
        return BaseResult.error();
    }

}
