package com.ego.service;

import com.ego.pojo.Goods;
import com.ego.result.BaseResult;

/**
 * Created by jick on 2019/3/30.
 */
public interface GoodsServiceI {
    /**
     *  商品列表 - 新增商品 - 保存
     *
     * @param goods
     * @return
     */
    //商品保存
    BaseResult  productSave(Goods  goods);


   // BaseResult   selectGoodsList(Goods goods, Integer pageNum, Integer pageSize);
    //分页查询所有商品列表
    BaseResult   selectGoodsList(Goods goods,Integer  pageNum,Integer pageSize);



}
