package com.ego.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ego.config.AlipayConfig;
import com.ego.pojo.Admin;
import com.ego.result.BaseResult;
import com.ego.result.CartResult;
import com.ego.service.CartServiceI;
import com.ego.service.OrderServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @see(功能介绍) : 订单Controller
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderServiceI orderService;

    @Autowired
    private CartServiceI cartService;

    /**
     * 查询redis购物车信息跳转页面展示
     *
     * @param model
     * @return
     */
    @RequestMapping("/preOrder")
    public String preOrder(HttpServletRequest request, Model model) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        model.addAttribute("cartResult", cartService.getCartList(admin));
        return "order/preOrder";
    }

    /**
     * 提交订单
     * 查询购物车信息
     * 生成订单数据
     * 清空购物车信息
     */
    @RequestMapping("/submitOrder")
    public String submitOrder(HttpServletRequest request, BigDecimal totalPrice, Model model) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        // 获取购物车信息
        CartResult cartResult = cartService.getCartList(admin);
        // 生成订单
        BaseResult result = orderService.orderSave(admin, cartResult);
        if (200 == result.getCode()) {
            // 清空购物车信息
            cartService.clearCart(admin);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("orderId", result.getMessage());
            return "order/submitOrder";
        }

        return "error";
    }

   /* *//**
     * 去付款
     */
    @RequestMapping("/pay")
    public String pay(HttpServletRequest request, String orderId, String totalPrice, Model model) {
        try {
            Admin admin = (Admin) request.getSession().getAttribute("user");
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = orderId;
            //付款金额，必填
            String total_amount = totalPrice;
            //订单名称，必填
            String subject = admin.getUserName() + "的订单！";
            //商品描述，可空
            String body = "";

            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            //输出
            model.addAttribute("result", result);
            return "order/pay";
        } catch (AlipayApiException e) {
            logger.error("付款失败，失败原因：" + e.getMessage());
        }
        return "error";
    }

}
