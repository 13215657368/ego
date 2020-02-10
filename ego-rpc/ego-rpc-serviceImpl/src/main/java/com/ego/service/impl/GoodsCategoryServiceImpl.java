package com.ego.service.impl;

import com.ego.mapper.GoodsCategoryMapper;
import com.ego.pojo.GoodsCategory;
import com.ego.pojo.GoodsCategoryExample;
import com.ego.service.GoodsCategoryServiceI;
import com.ego.util.JsonUtil;
import com.ego.vo.GoodsCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类服务实现类
 */
public class GoodsCategoryServiceImpl implements GoodsCategoryServiceI {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 查询所有商品分类
     *
     * @return
     */
    @Override
    public List<GoodsCategoryVo> selectCategoryList() {
        // 创建example对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        // 设置查询参数
        example.createCriteria().andParentIdEqualTo((short) 0).andLevelEqualTo((byte) 1);
        // 查询一级分类
        List<GoodsCategory> gcList1 = null;
        // 查询缓存
        String goodsCategoryListKey = "goodsCategory:list";
        String levelStr1 = redisTemplate.opsForValue().get(goodsCategoryListKey);
        if (null != levelStr1 && levelStr1.length() > 0) {
            gcList1 = JsonUtil.jsonToList(levelStr1, GoodsCategory.class);
        } else {
            gcList1 = goodsCategoryMapper.selectByExample(example);
            redisTemplate.opsForValue().set(goodsCategoryListKey, JsonUtil.object2JsonStr(gcList1));
        }

        // 创建一级分类vo
        List<GoodsCategoryVo> gcvList1 = new ArrayList<>();

        for (int i = 0; i < gcList1.size(); i++) {
            GoodsCategoryVo gcv1 = new GoodsCategoryVo();
            BeanUtils.copyProperties(gcList1.get(i), gcv1);
            // 清除查询参数
            example.clear();
            // 设置查询参数
            example.createCriteria().andParentIdEqualTo(gcv1.getId()).andLevelEqualTo((byte) 2);
            // 查询二级分类
            List<GoodsCategory> gcList2 = goodsCategoryMapper.selectByExample(example);
            // 创建二级分类vo
            List<GoodsCategoryVo> gcvList2 = new ArrayList<>();

            for (int j = 0; j < gcList2.size(); j++) {
                GoodsCategoryVo gcv2 = new GoodsCategoryVo();
                BeanUtils.copyProperties(gcList2.get(j), gcv2);
                // 清除查询参数
                example.clear();
                // 设置查询参数
                example.createCriteria().andParentIdEqualTo(gcv2.getId()).andLevelEqualTo((byte) 3);
                // 查询三级分类
                List<GoodsCategory> gcList3 = goodsCategoryMapper.selectByExample(example);
                // 创建三级分类vo
                List<GoodsCategoryVo> gcvList3 = new ArrayList<>();

                for (int k = 0; k < gcList3.size(); k++) {
                    GoodsCategoryVo gcv3 = new GoodsCategoryVo();
                    BeanUtils.copyProperties(gcList3.get(k), gcv3);
                    // 添加至三级vo集合
                    gcvList3.add(gcv3);
                }
                // 将三级分类添加至二级分类
                gcv2.setChildren(gcvList3);
                // 添加至二级vo集合
                gcvList2.add(gcv2);
            }

            // 将二级分类添加至一级分类
            gcv1.setChildren(gcvList2);
            // 添加至一级vo集合
            gcvList1.add(gcv1);
        }

        return gcvList1;
    }

}
