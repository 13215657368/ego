package com.ego.service.impl;

import com.ego.enums.*;
import com.ego.mapper.OrderGoodsMapper;
import com.ego.mapper.OrderMapper;
import com.ego.pojo.Admin;
import com.ego.pojo.Order;
import com.ego.pojo.OrderGoods;
import com.ego.result.BaseResult;
import com.ego.result.CartResult;
import com.ego.service.OrderServiceI;
import com.ego.util.DateUtil;
import com.ego.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单service
 */
@Service
public class OrderServiceImpl implements OrderServiceI {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${order.increment}")
    private String orderIncrement;

    /**
     * 保存订单
     *
     * @param cartResult
     * @return
     */
    @Override
    public BaseResult orderSave(Admin admin, CartResult cartResult) {
        // 创建订单对象
        Order order = new Order();
        // 订单编号ego-年月日时分秒-自增编号
        order.setOrderSn("ego-"
                + DateUtil.getDateStr(LocalDateTime.now(), "yyyyMMddHHmmss")
                + "-" + getIncrement());
        // 用户id
        order.setUserId(Integer.valueOf(admin.getAdminId()));
        // 订单状态
        order.setOrderStatus(OrderStatus.no_confirm.getStatus());
        // 发货状态
        order.setShippingCode(String.valueOf(ShipStatus.no_send.getStatus()));
        // 支付状态
        order.setPayStatus(PayStatus.no_pay.getStatus());
        // 收货人
        order.setConsignee(admin.getUserName());
        // 应付款金额
        order.setOrderAmount(cartResult.getTotalPrice());
        // 订单总价
        order.setTotalAmount(cartResult.getTotalPrice());
        // 下单时间
        Long addTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        order.setAddTime(addTime.intValue());
        // 保存订单
        int result = orderMapper.insertSelective(order);
        if (result > 0) {
            List<OrderGoods> orderGoodsList = new ArrayList<>();
            // 循环遍历构建订单商品集合
            for (GoodsVo goodsVo : cartResult.getGoodsVoList()) {
                // 创建订单商品对象
                OrderGoods orderGoods = new OrderGoods();
                // 订单id
                orderGoods.setOrderId(order.getOrderId());
                // 商品id
                orderGoods.setGoodsId(goodsVo.getGoodsId());
                // 购买数量
                orderGoods.setGoodsNum(goodsVo.getGoodsNum().shortValue());
                // 市场价
                orderGoods.setMarketPrice(goodsVo.getMarketPrice());
                // 商品名称
                orderGoods.setGoodsName(goodsVo.getGoodsName());
                // 订单类型
                orderGoods.setPromType(OrderWay.normal.getStatus());
                // 发货状态
                orderGoods.setIsSend(SendStatus.no_pay.getStatus());
                // 添加至list
                orderGoodsList.add(orderGoods);
            }
            // 保存订单商品集合
            result = orderGoodsMapper.insertBatchSelective(orderGoodsList);
            if (result > 0) {
                BaseResult rs = BaseResult.success();
                rs.setMessage(String.valueOf(order.getOrderId()));
                return rs;
            }
        }

        return BaseResult.error();
    }

    /**
     * 获取自增的key
     *
     * @return
     */
    private Long getIncrement() {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(orderIncrement, redisTemplate.getConnectionFactory());
        return entityIdCounter.getAndIncrement();
    }

}
