package com.ego;

import com.ego.util.ReadHtmlUtil;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class JavaMailTest {
    /**
     * 简单版邮件发送
     *
     * @throws MessagingException
     */
    @Test
    public void testSendMail01() throws MessagingException {
//  创建参数配置对象 ,  用于连接邮件服务器的参数配置
        Properties props = new Properties();
//  开启 debug 调试
        props.setProperty("mail.debug", "true");
//  发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
//  设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.163.com");
//  发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
//  设置环境信息
        Session session = Session.getInstance(props);
//  创建邮件对象
        Message msg = new MimeMessage(session);
//  设置邮件主题
        msg.setSubject("EGO  商城注册成功邮件");
//  设置邮件内容
        msg.setText(" 一封由 EGO  商城发送的邮件！");
//  设置发件人
        msg.setFrom(new InternetAddress("13215657368@163.com"));
//  根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
//  连接邮件服务器
        transport.connect("13215657368@163.com", "yubo123");
//  发送邮件
        transport.sendMessage(msg, new Address[]{new InternetAddress("2956079321@qq.com")});
//  关闭连接
        transport.close();
    }

    // --------------------- 为封装 Util 做准备 -------------------------
//  发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
// PS ：某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为 “ 授权码 ” ）
//  对于开启了独立密码的邮箱 ,  这里的邮箱密码必需使用这个独立密码（授权码）
    public static String emailAccount = "13215657368@163.com";
    public static String emailPassword = "yubo123";
    //  发件人邮箱的 SMTP 服务器地址 ,  必须准确 ,  不同邮件服务器地址不同 ,  一般 ( 只是一般 ,  绝非绝对 ) 格式为 : smtp.xxx.com
//  网易 163 邮箱的 SMTP 服务器地址为 : smtp.163.com
    public static String emailHost = "smtp.163.com";
    //  收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMail = "2956079321@qq.com";

    /**
     * JavaMail 邮件发送
     *
     * @throws Exception
     */
    @Test
    public void testSendMail02() throws MessagingException, UnsupportedEncodingException {
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
// PS:  某些邮箱服务器要求 SMTP  连接需要使用 SSL  安全认证 ( 为了提高安全性 ,  邮箱支持 SSL 连接 ,  也可以自己开启 ),
//  如果无法连接邮件服务器 ,  仔细查看控制台打印的 log,  如果有有类似 “ 连接失败 ,  要求 SSL  安全连接 ”  等错误 ,
//  打开下面 /* ... */  之间的注释代码 ,  开启 SSL  安全连接。
/*
* // SMTP  服务器的端口 ( 非 SSL  连接的端口一般默认为 25,  可以不添加 ,  如果开启了 SSL  连接 , //
*  需要改为对应邮箱的 SMTP  服务器的端口 ,  具体可查看对应邮箱服务的帮助 , // QQ 邮箱的 SMTP(SLL) 端口为 465 或 587,
*  其他邮箱自行去查看 ) final String smtpPort = "465";
* props.setProperty("mail.smtp.port", smtpPort);
* props.setProperty("mail.smtp.socketFactory.class",
* "javax.net.ssl.SSLSocketFactory");
* props.setProperty("mail.smtp.socketFactory.fallback", "false");
* props.setProperty("mail.smtp.socketFactory.port", smtpPort);
*/
// 2.  根据配置创建会话对象 ,  用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true); //  设置为 debug 模式 ,  可以查看详细的发送 log
// 3.  创建一封邮件
        MimeMessage message = new MimeMessage(session);
// 4. From:  发件人
        message.setFrom(new InternetAddress(emailAccount, " 发件人昵称", "UTF-8"));
// 5. To:  收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, " 收件人名称", "UTF-8"));
// To:  增加收件人（可选）
//message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("1530529976@qq.com", " 波波老师 ","UTF-8"));
// Cc:  抄送（可选）
//message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("396424335@qq.com", "Tony 老师 ","UTF-8"));
// 4. Subject:  邮件主题
        message.setSubject("EGO  商城帐号注册成功邮件", "UTF-8");
// 5. Content:  邮件正文（可以使用 html 标签）
//  读取 html 文件内容
        String info = ReadHtmlUtil.getMailString("registerEmail");
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
// 6.  设置发件时间
        message.setSentDate(new Date());
// 7.  保存设置
        message.saveChanges();
// 8.  将该邮件保存到本地
//OutputStream out = new FileOutputStream("C://MyEmail" + UUID.randomUUID().toString() + ".eml");
//message.writeTo(out);
//out.flush();
//out.close();
// 9.  根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
// 10.  使用 邮箱账号 和 密码 连接邮件服务器 ,  这里认证的邮箱必须与 message  中的发件人邮箱一致 ,  否则报错
//
// PS_01:  成败的判断关键在此一句 ,  如果连接服务器失败 ,  都会在控制台输出相应失败原因的 log,
//  仔细查看失败原因 ,  有些邮箱服务器会返回错误码或查看错误类型的链接 ,  根据给出的错误
//  类型到对应邮件服务器的帮助网站上查看具体失败原因。
//
// PS_02:  连接失败的原因通常为以下几点 ,  仔细检查代码 :
// (1)  邮箱没有开启 SMTP  服务 ;
// (2)  邮箱密码错误 ,  例如某些邮箱开启了独立密码 ;
// (3)  邮箱服务器要求必须要使用 SSL  安全连接 ;
// (4)  请求过于频繁或其他原因 ,  被邮件服务器拒绝服务 ;
// (5)  如果以上几点都确定无误 ,  到邮件服务器网站查找帮助。
//
// PS_03:  仔细看 log,  认真看 log,  看懂 log,  错误原因都在 log 已说明。
        transport.connect(emailAccount, emailPassword);
// 11.  发送邮件 ,  发到所有的收件地址 , message.getAllRecipients()  获取到的是在创建邮件对象时添加的所有收件人 ,  抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
// 12.  关闭连接
        transport.close();
    }
}