package com.ego.service.impl;

import com.ego.service.HelloServiceI;

/**
 * 测试服务实现类
 */
public class HelloServiceImpl implements HelloServiceI {

    @Override
    public String sayHello(String name) {
        System.out.println("HelloServiceImpl");
        return "Hello " + name;
    }

}
