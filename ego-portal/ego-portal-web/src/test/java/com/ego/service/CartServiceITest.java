package com.ego.service;

import com.ego.pojo.Admin;
import com.ego.result.BaseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jick on 2019/4/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        "classpath:spring/applicationContext-*.xml")
public class CartServiceITest {
    @Autowired
    private  CartServiceI  cartService;

    /**
     *  测试添加至购物车
     */
  /*  @Test
    public void testAddToCart() {
        Admin admin = new Admin();
        admin.setAdminId((short) 1);
//  实例化购物车信息
        CartVo cart = new CartVo();
        cart.setGoodsId(12345);
        cart.setAddTime(new Date());
        cart.setGoodsName(" 超级手机第一代");
        cart.setGoodsNum(99);
        cart.setMarketPrice(new BigDecimal(998));
        BaseResult result = cartService.addToCart(cart, admin);
        System.out.println(result.getCode());*/
  //  }
    /**
     *  测试查询购物车数量
     */
    @Test
    public void testGetCartNum() {
        Admin admin = new Admin();
        admin.setAdminId((short) 1);
        Integer cartNum = cartService.getCartNum(admin);
        System.out.println("cartNum:" + cartNum);
    }

}
