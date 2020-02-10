package com.ego.service.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SsoRun {
    private final static Logger logger =
            LoggerFactory.getLogger(SsoRun.class);

    public static void main(String[] args) {
// 1. 加载 spring 配置文件
        String config = "classpath:spring/applicationContext-*.xml";
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext(config);
// 2. 启动
        context.start();
// 3.while 循环防止自动关闭
        while (true) {
            try {
                Thread.sleep(10000);
                logger.info("Dubbo  服务主进程正在服务！");
            } catch (Exception e) {
                logger.error("Dubbo  服务主进程异常：" + e.getMessage());
            }
        }
    }
}