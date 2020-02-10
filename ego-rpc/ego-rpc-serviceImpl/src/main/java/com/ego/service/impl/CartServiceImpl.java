package com.ego.service.impl;

import com.ego.pojo.Admin;
import com.ego.result.BaseResult;
import com.ego.result.CartResult;
import com.ego.service.CartServiceI;
import com.ego.util.JsonUtil;
import com.ego.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车服务
 */
public class CartServiceImpl implements CartServiceI {

    @Value("${user.cart}")
    private String userCart;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 添加至购物车
     *
     * @param goodsVo
     * @return
     */
    @Override
    public BaseResult addToCart(Admin admin, GoodsVo goodsVo) {
        // 判断用户是否为空
        if (null == admin)
            return BaseResult.error();

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        // 先查询
        Map<String, String> resultMap = hashOperations.entries(userCart + ":" + admin.getAdminId());
        // 判断map是否为空
        if (!resultMap.isEmpty() && resultMap.size() > 0) {
            // 根据map的key获取value
            String goodsVoStr = resultMap.get(String.valueOf(goodsVo.getGoodsId()));
            // 判断goodsVoStr是否为空
            // 修改商品购物车信息
            if (null != goodsVoStr && goodsVoStr.length() > 0) {
                // 原数据
                GoodsVo oldGvo = JsonUtil.jsonStr2Object(goodsVoStr, GoodsVo.class);
                // 计算商品数量，重新赋值商品价格
                oldGvo.setGoodsNum(oldGvo.getGoodsNum() + goodsVo.getGoodsNum());
                oldGvo.setMarketPrice(goodsVo.getMarketPrice());
                // 重新添加至map
                resultMap.put(String.valueOf(goodsVo.getGoodsId()), JsonUtil.object2JsonStr(oldGvo));
            } else {
                // 新增商品购物车信息
                resultMap.put(String.valueOf(goodsVo.getGoodsId()), JsonUtil.object2JsonStr(goodsVo));
            }
        } else {
            resultMap = new HashMap<>();
            // 新增商品购物车信息
            resultMap.put(String.valueOf(goodsVo.getGoodsId()), JsonUtil.object2JsonStr(goodsVo));
        }

        // 添加至redis
        hashOperations.putAll(userCart + ":" + admin.getAdminId(), resultMap);
        return BaseResult.success();
    }

    /**
     * 获取购物车数量
     *
     * @return
     */
    @Override
    public Integer getCartNum(Admin admin) {
        Integer total = 0;
        // 判断用户是否为空
        if (null == admin)
            return total;

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        // 先查询
        Map<String, String> resultMap = hashOperations.entries(userCart + ":" + admin.getAdminId());
        // 判断map是否为空
        if (!resultMap.isEmpty() && resultMap.size() > 0) {
            // 循环累加
            for (Map.Entry<String, String> map : resultMap.entrySet()) {
                GoodsVo goodsVo = JsonUtil.jsonStr2Object(map.getValue(), GoodsVo.class);
                total += goodsVo.getGoodsNum();
            }
        }
        return total;
    }

    /**
     * 查询购物车列表
     *
     * @param admin
     * @return
     */
    @Override
    public CartResult getCartList(Admin admin) {
        CartResult cartResult = null;

        // 判断用户是否为空
        if (null == admin)
            return cartResult;

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        // 先查询
        Map<String, String> resultMap = hashOperations.entries(userCart + ":" + admin.getAdminId());
        // 判断map是否为空
        if (!resultMap.isEmpty() && resultMap.size() > 0) {
            // 初始化返回对象
            cartResult = new CartResult();
            List<GoodsVo> goodsVoList = new ArrayList<>();
            BigDecimal totalPrice = new BigDecimal(0);
            // 循环获取
            for (Map.Entry<String, String> map : resultMap.entrySet()) {
                GoodsVo goodsVo = JsonUtil.jsonStr2Object(map.getValue(), GoodsVo.class);
                // 计算总价格
                // 首先是商品数量乘以单价，然后每一个单独的总价相加
                // 四舍五入保留两位小数，不足0补齐
                String marketPriceFormat = new DecimalFormat("0.00").format(goodsVo.getMarketPrice());
                goodsVo.setMarketPrice(new BigDecimal(marketPriceFormat));
                BigDecimal singleTotal = goodsVo.getMarketPrice().multiply(new BigDecimal(goodsVo.getGoodsNum()));
                totalPrice = totalPrice.add(singleTotal);
                // 添加至列表
                goodsVoList.add(goodsVo);
            }
            cartResult.setGoodsVoList(goodsVoList);
            cartResult.setTotalPrice(totalPrice);
        }
        return cartResult;
    }

    /**
     * 删除购物车
     *
     * @param admin
     * @return
     */
    @Override
    public BaseResult deleteCart(Admin admin, Integer goodsId) {
        // 判断用户是否为空
        if (null == admin)
            return BaseResult.error();

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        // 先查询
        Map<String, String> resultMap = hashOperations.entries(userCart + ":" + admin.getAdminId());
        // 判断map是否为空
        if (!resultMap.isEmpty() && resultMap.size() > 0) {
            String goodsVoStr = resultMap.get(String.valueOf(goodsId));
            if (null != goodsVoStr && goodsVoStr.length() > 0) {
                hashOperations.delete(userCart + ":" + admin.getAdminId(), String.valueOf(goodsId));
                return BaseResult.success();
            }
        }
        return BaseResult.error();
    }

    /**
     * 清空购物单车
     *
     * @param admin
     * @return
     */
    @Override
    public BaseResult clearCart(Admin admin) {
        // 判断用户是否为空
        if (null == admin)
            return BaseResult.error();

        redisTemplate.delete(userCart + ":" + admin.getAdminId());
        return BaseResult.success();
    }

}
