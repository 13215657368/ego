package com.ego.service.impl;

import com.ego.mapper.GoodsCategoryMapper;
import com.ego.pojo.GoodsCategory;
import com.ego.pojo.GoodsCategoryExample;
import com.ego.result.BaseResult;
import com.ego.service.GoodsCategoryServiceI;
import com.ego.util.JsonUtil;
import com.ego.vo.GoodsCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jick on 2019/3/28.
 */
@Service
public class GoodsCategoryServiceImpl  implements GoodsCategoryServiceI {

    @Autowired
    private GoodsCategoryMapper   goodsCategoryMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public List<GoodsCategory> selectTopCategory() {
        //创建查询对象
        GoodsCategoryExample     example  =   new GoodsCategoryExample();
        //社会参数
        example.createCriteria().andParentIdEqualTo((short) 0);

        return   goodsCategoryMapper.selectByExample(example);
    }

    @Override
    public List<GoodsCategory> selectCategoryGetByParentId(Short parentId) {

        GoodsCategoryExample   example  =  new GoodsCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        return   goodsCategoryMapper.selectByExample(example);
    }

    @Override
    public BaseResult categotySave(GoodsCategory goodsCategory) {

        int   result  =  goodsCategoryMapper.insertSelective(goodsCategory);

        if(result>0){
            //清除redis缓存
            Set<String> keys  =  redisTemplate.keys("goodsCategory:*");
            redisTemplate.delete(keys);
            return   BaseResult.success();
        }else{
            return  BaseResult.error();
        }

        //return   result >0 ?  BaseResult.success() :   BaseResult.error();

    }

    @Override
    public List<GoodsCategoryVo> selectCategoryListFroView() {

        GoodsCategoryExample   example  =  new GoodsCategoryExample();

        example.createCriteria().andParentIdEqualTo((short) 0).andLevelEqualTo((byte) 1);

        //查询操作

        List<GoodsCategory>  gcList1 =  null;
        String   goodsCategoryListKey = "goodsCategory:list";
        String    levelStrl   = redisTemplate.opsForValue().get(goodsCategoryListKey);
        if(null !=levelStrl && levelStrl.length()>0){
            gcList1 = JsonUtil.jsonToList(levelStrl,GoodsCategory.class);
        }else{
            gcList1 = goodsCategoryMapper.selectByExample(example);
            redisTemplate.opsForValue().set(goodsCategoryListKey, JsonUtil.object2JsonStr(gcList1));
        }




        //创建返回结果集合   第一级

        List<GoodsCategoryVo>  gcvList1 = new ArrayList<>();

        for(GoodsCategory gc1:gcList1){
            //创建vo对象
            GoodsCategoryVo   gcv1  = new GoodsCategoryVo();

            //赋值属性

            BeanUtils.copyProperties(gc1,gcv1);//sour  target

            //查询第二阶段
            //清空第一次查询的参数
            example.clear();

            //重新指定where条件  parentID ，2
            example.createCriteria().andParentIdEqualTo(gc1.getId()).andLevelEqualTo((byte) 2);
           List<GoodsCategory>  gcList2 =   goodsCategoryMapper.selectByExample(example);

           //创建返回结果集合   第二集
            List<GoodsCategoryVo>  gcvList2 =  new ArrayList<>();

            for(GoodsCategory gc2 :gcList2 ){
                //创建vo对象
                GoodsCategoryVo  gcv2  = new GoodsCategoryVo();
                BeanUtils.copyProperties(gc2,gcv2);

                //清空条件
                example.clear();


                //重新指定where条件parentId  3
                example.createCriteria().andParentIdEqualTo(gc2.getId()).andLevelEqualTo((byte) 3);

                List<GoodsCategory>  gcList3 =  goodsCategoryMapper.selectByExample(example);

                //创建返回结果集合  第三季
                List<GoodsCategoryVo>  gcvList3 =  new ArrayList<>();
                for(GoodsCategory  gc3 : gcList3){
                    GoodsCategoryVo  gcv3  =  new GoodsCategoryVo();

                    //赋值属性
                    BeanUtils.copyProperties(gc3,gcv3);
                    gcvList3.add(gcv3);
                }

                gcv2.setChildren(gcvList3);
                gcvList2.add(gcv2);

            }

            gcv1.setChildren(gcvList2);
            gcvList1.add(gcv1);


        }


        return  gcvList1;
    }

    /**
     * 查询所有分类给商品列表使用
     *
     * @return
     */
    @Override
    public List<GoodsCategory> selectCategoryListForGoodsList() {
        return goodsCategoryMapper.selectByExample(new GoodsCategoryExample());
    }

    @Override
    public BaseResult categoryDelete(Short id) {
        int  result =   goodsCategoryMapper.deleteByPrimaryKey(id);

       // return  result>0 ?  BaseResult.success() : BaseResult.error();

        if(result>0){
            //清除redis缓存
            Set<String> keys  =  redisTemplate.keys("goodsCategory:*");
            redisTemplate.delete(keys);
            return   BaseResult.success();
        }else{
            return  BaseResult.error();
        }
    }

    @Override
    public BaseResult categoryListDelete(Short[] ids) {

        int  result =   goodsCategoryMapper.deleBathByPrimaryKey(ids);

        if(result>0){
            //清除redis缓存
            Set<String> keys  =  redisTemplate.keys("goodsCategory:*");
            redisTemplate.delete(keys);
            return   BaseResult.success();
        }else{
            return  BaseResult.error();
        }

    }

    @Override
    public GoodsCategory categoryGetById(Short id) {
         return    goodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public BaseResult categoryUpdate(GoodsCategory goodsCategory) {

        int  result  = goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
       // return     result>0 ?   BaseResult.success() :  BaseResult.error();
        if(result>0){
            //清除redis缓存
            Set<String> keys  =  redisTemplate.keys("goodsCategory:*");
            redisTemplate.delete(keys);
            return   BaseResult.success();
        }else{
            return  BaseResult.error();
        }
    }


}
