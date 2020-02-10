package com.ego.enums;

/**
 * @see(功能介绍) : 订单商品表发货状态枚举类
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
public enum SendStatus {
    // 0未发货，1已发货，2已换货，3已退货
    no_pay((byte) 0, "未支付"),
    has_payed((byte) 1, "已支付"),
    changed((byte) 2, "已换货"),
    refund((byte) 3, "已退货");

    //状态
    private Byte status;
    //描述
    private String message;

    //自定义构造器
    private SendStatus(Byte status, String message) {
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
