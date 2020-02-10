package com.ego.service;

import com.ego.vo.GoodsCategoryVo;

import java.util.List;

/**
 * 商品分类服务
 */
public interface GoodsCategoryServiceI {

    /**
     * 查询所有分类
     */
    List<GoodsCategoryVo> selectCategoryList();

}
