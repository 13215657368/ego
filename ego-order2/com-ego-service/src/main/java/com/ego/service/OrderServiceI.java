package com.ego.service;

import com.ego.pojo.Admin;
import com.ego.result.BaseResult;
import com.ego.result.CartResult;

/**
 * 订单service
 */
public interface OrderServiceI {

    /**
     * 保存订单
     */
    BaseResult orderSave(Admin admin, CartResult cartResult);

}
