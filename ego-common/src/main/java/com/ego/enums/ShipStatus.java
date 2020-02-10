package com.ego.enums;

/**
 * @see(功能介绍) : 订单表发货状态枚举类
 * @version(版本号) : 1.0
 * @author(创建人) : Dylan
 * @since : JDK 1.8
 */
public enum ShipStatus {
    // 0未发货 1已发货 2 部分发货
    no_send((byte) 0, "未发货"),
    has_send((byte) 1, "已发货"),
    part_send((byte) 2, "部分发货");
    //状态
    private Byte status;
    //描述
    private String message;

    //自定义构造器
    private ShipStatus(Byte status, String message) {
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
