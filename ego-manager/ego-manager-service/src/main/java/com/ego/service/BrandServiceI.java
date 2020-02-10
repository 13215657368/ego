package com.ego.service;

import com.ego.pojo.Brand;

import java.util.List;

/**
 * Created by jick on 2019/3/30.
 */
public interface BrandServiceI {
    //查询所有商品的品牌
    List<Brand> selectBrandList();

}
