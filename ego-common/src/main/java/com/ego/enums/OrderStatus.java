package com.ego.enums;

/**
 * @see(功能介绍) : 订单状态枚举类
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
public enum OrderStatus {
    // 0待确认 1已确认 2已收货 3已取消 4已完成 5已作废
    no_confirm((byte) 0, "待确认"),
    has_confirm((byte) 1, "已确认"),
    has_recieved((byte) 2, "已收货"),
    cancled((byte) 3, "已取消"),
    completed((byte) 4, "已完成"),
    discard((byte) 5, "已作废");

    // 状态
    private Byte status;
    // 描述
    private String message;

    // 自定义构造器
    private OrderStatus(Byte status, String message) {
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
