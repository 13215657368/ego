package com.ego.service.impl;

import com.ego.mapper.GoodsMapper;
import com.ego.pojo.Goods;
import com.ego.pojo.GoodsExample;
import com.ego.result.BaseResult;
import com.ego.service.GoodsServiceI;
import com.ego.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

/**
 * Created by jick on 2019/3/30.
 */
@Service
public class GoodsServiceImpl  implements GoodsServiceI {
    @Autowired
    private GoodsMapper    goodsMapper;
    @Autowired
    private RedisTemplate<String,String>  redisTemplate;


    @Override
    public BaseResult productSave(Goods goods) {

        int result  =   goodsMapper.insertSelective(goods);

        if(result>0){
            //清除redis缓存
            Set<String> keys  =  redisTemplate.keys("goods:*");
            redisTemplate.delete(keys);
            BaseResult  baseResult  =  BaseResult.success();
            baseResult.setMessage(String.valueOf(goods.getGoodsId()));
            return    baseResult;
        }
        return    BaseResult.error();


    }


    /*分页查询所有商品的列表*/
    @Override
    public BaseResult selectGoodsList(Goods goods, Integer pageNum, Integer pageSize) {
        //创建example对象
        GoodsExample example  =  new GoodsExample();
        //创建查询对象
        GoodsExample.Criteria  criteria  =  example.createCriteria();
        //构建分页对象
        PageHelper.startPage(pageNum,pageSize);


         /*
            分析编写redis key
                1. 无参数(分页)
                    goods:pageNum_1:pageSize_10:carId_:brandId_:goodsName_
                2. 有参数
                    goods:pageNum_1:pageSize_10:carId_123:brandId_:goodsName_ 根据分类查询
                    goods:pageNum_1:pageSize_10:carId_:brandId_123:goodsName_ 根据品牌查询
                    goods:pageNum_1:pageSize_10:carId_:brandId_:goodsName_OPPO 根据商品名称查询
                    goods:pageNum_1:pageSize_10:carId_123:brandId_123:goodsName_ 根据分类和品牌查询
                    goods:pageNum_1:pageSize_10:carId_:brandId_123:goodsName_OPPO 根据品牌和商品名称查询
                    goods:pageNum_1:pageSize_10:carId_123:brandId_:goodsName_OPPO 根据分类和商品名称查询
         */

/*
         String[] goodsArrayKey  =  {"goods:pageNum_"+pageNum+":pageSize_"+pageSize,
                                    ":carId_",":brandId_",":goodsName_"};*/
         String[]  goodsArrayKey ={"goods:pageNum_"+pageNum+":pageSize"+pageSize,":cartId_",
                                      ":brandId_",":goodsName_"};


        //处理查询参数
        //如果传递过来的参数不为空，赋值，同时对应的·key值改变
        if(null!=goods.getCatId()&&0!=goods.getCatId()){
            criteria.andCatIdEqualTo(goods.getCatId());
            goodsArrayKey[1]=":cartId_"+goods.getCatId();
        }

        if(null!=goods.getBrandId()&&0!=goods.getBrandId()){
            criteria.andBrandIdEqualTo(goods.getBrandId());
            goodsArrayKey[2]=":brandId_"+goods.getBrandId();
        }

        if(null!=goods.getGoodsName()&&goods.getGoodsName().trim().length()>0){
            criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
            goodsArrayKey[3]=":goodsName_"+goods.getGoodsName();
        }


        //将数组转换成字符串，便于查询
        String  goodsKey = "";
        for(int i=0;i<goodsArrayKey.length;i++){
            goodsKey+=goodsArrayKey[i];
        }

        //查询缓存数据库，有值，则从缓存数据库中拿值
        PageInfo<Goods>  pageInfo = null;
        String   listStr = redisTemplate.opsForValue().get(goodsKey);
        if(listStr!=null){
            pageInfo =  JsonUtil.jsonStr2Object(listStr,PageInfo.class);
            BaseResult  result =  BaseResult.success(pageInfo);
            return   result;

        }

        //没有数据则写入缓存数据库
          List<Goods> list = goodsMapper.selectByExample(example);

        //如果查询到的数据不为空，为存入到缓存数据库
        if(null!=list&&!list.isEmpty()){
          //  redisTemplate.opsForValue().multiSet();
            pageInfo =  new PageInfo<>(list);
            redisTemplate.opsForValue().set(goodsKey,JsonUtil.object2JsonStr(pageInfo));
            BaseResult result =BaseResult.success();
            return  result;
        }

        return  BaseResult.error();
    }

}
