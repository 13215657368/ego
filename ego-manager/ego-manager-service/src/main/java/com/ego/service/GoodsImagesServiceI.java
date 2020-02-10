package com.ego.service;

import com.ego.result.BaseResult;

/**
 * Created by jick on 2019/3/31.
 */
public interface GoodsImagesServiceI {

    //添加商品的相册
    BaseResult  goodsImagesSave(String  fileUrl,Integer id);
}
