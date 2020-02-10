package com.ego.service.impl;

import com.ego.mapper.GoodsImagesMapper;
import com.ego.pojo.GoodsImages;
import com.ego.result.BaseResult;
import com.ego.service.GoodsImagesServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jick on 2019/3/31.
 */
@Service
//商品列表--新增商品-商品相册-保存
public class GoodsImagesServiceImpl implements GoodsImagesServiceI {
    @Autowired
    private GoodsImagesMapper goodsImagesMapper;

    @Override
    public BaseResult goodsImagesSave(String fileUrl, Integer id) {
        //将商品的url和id存入到数据库中

        if(fileUrl !=null && id !=null) {
            //1创建GoodsImages对象
            GoodsImages goodsImages = new GoodsImages();
            //2、将信息存入到对象中
            goodsImages.setGoodsId(id);
            goodsImages.setImageUrl(fileUrl);
            //3、调用插叙函数
            int result = goodsImagesMapper.insertSelective(goodsImages);

            if(result>0){
                return  BaseResult.success();
            }

        }
        return  BaseResult.error();
    }

}
