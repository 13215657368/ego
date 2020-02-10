package com.ego.service;

import com.ego.pojo.Admin;
import com.ego.result.BaseResult;
import com.ego.result.CartResult;
import com.ego.vo.GoodsVo;

/**
 * 购物车服务
 */
public interface CartServiceI {

    /**
     * 添加至购物车
     */
    BaseResult addToCart(Admin admin, GoodsVo goodsVo);


    /**
     * 查询购物车数量
     */
    Integer getCartNum(Admin admin);

    /**
     * 查询购物车列表
     */
    CartResult getCartList(Admin admin);

    /**
     * 删除购物车单条
     */
    BaseResult deleteCart(Admin admin, Integer goodsId);

    /**
     * 清空购物车
     */
    BaseResult clearCart(Admin admin);

}
