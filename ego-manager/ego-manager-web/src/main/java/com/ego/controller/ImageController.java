package com.ego.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private Producer producer;

    @RequestMapping("/getKaptchaImage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse
            response) throws Exception {
        response.setDateHeader("Expires", 0);
// Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
// Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
// return a jpeg
        response.setContentType("image/jpeg");
/********************* 生成验证码 ******begin*******************/
//  获取验证码文本内容
        String capText = producer.createText();
        System.out.println(" 验证码:" + capText);
//  将验证码存入 session
        request.getSession().setAttribute("pictureVerifyKey", capText);
//  根据验证码文本内容创建图形验证码
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        try {
//  写出图形验证码格式为 jpg
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
/********************* 生成验证码 ******end*******************/
    }
}