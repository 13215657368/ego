package com.ego.controller;

import com.ego.pojo.Admin;
import com.ego.service.CartServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车controller
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    protected CartServiceI cartService;

    /**
     * 查询购物车数量
     */
    @RequestMapping("/getCartNum")
    @ResponseBody
    public Integer getCartNum(HttpServletRequest request) {
        // 从session获取用户信息
        Admin admin = (Admin) request.getSession().getAttribute("user");
        // 获取购物车数量
        return cartService.getCartNum(admin);
    }

}
