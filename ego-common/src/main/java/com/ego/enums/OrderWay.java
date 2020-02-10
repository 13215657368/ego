package com.ego.enums;

/**
 * @see(功能介绍) : 订单方式枚举类
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
public enum OrderWay {
    // 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
    normal((byte) 0, "普通订单"),
    xsqg((byte) 1, " 限时抢购"),
    tg((byte) 2, "团购 "),
    cxyh((byte) 3, "促销优惠");

    // 状态
    private Byte status;
    // 描述
    private String message;

    // 自定义构造器
    private OrderWay(Byte status, String message) {
        this.status = status;
        this.message = message;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
