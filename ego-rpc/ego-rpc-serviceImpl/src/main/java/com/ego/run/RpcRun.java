package com.ego.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RpcRun {

    private static Logger logger = LoggerFactory.getLogger(RpcRun.class);

    public static void main(String[] args) {
        // 1.加载spring配置文件
        String config = "classpath:spring/applicationContext-*.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        // 2.启动
        context.start();
        // 3.服务永久启动
        while (true) {
            try {
                logger.info("DUBBO服务正在运行中");
                Thread.sleep(10000);
            } catch (Exception e) {
                logger.info("DUBBO服务运行异常，异常：" + e.getMessage());
            }
        }
    }

}
