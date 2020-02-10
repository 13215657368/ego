package com.ego.enums;

/**
 * @see(功能介绍) : 支付状态枚举类
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
public enum PayStatus {
    // 0未支付 1已支付 2部分支付
    no_pay((byte) 0, "未支付"),
    has_payed((byte) 1, "已支付"),
    part_pay((byte) 2, "部分支付");

    // 状态
    private Byte status;
    // 描述
    private String message;

    // 自定义构造器
    private PayStatus(Byte status, String message) {
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
