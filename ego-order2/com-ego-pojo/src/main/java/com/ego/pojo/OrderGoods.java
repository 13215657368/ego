package com.ego.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderGoods implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.rec_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Integer recId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.order_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.goods_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Integer goodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.goods_name
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private String goodsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.goods_sn
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private String goodsSn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.goods_num
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Short goodsNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.market_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private BigDecimal marketPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.goods_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private BigDecimal goodsPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.cost_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private BigDecimal costPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.member_goods_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private BigDecimal memberGoodsPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.give_integral
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Integer giveIntegral;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.spec_key
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private String specKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.spec_key_name
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private String specKeyName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.bar_code
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private String barCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.is_comment
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Byte isComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.prom_type
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Byte promType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.prom_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Integer promId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.is_send
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Byte isSend;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.delivery_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private Integer deliveryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_goods.sku
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private String sku;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_goods
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.rec_id
     *
     * @return the value of t_order_goods.rec_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Integer getRecId() {
        return recId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.rec_id
     *
     * @param recId the value for t_order_goods.rec_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.order_id
     *
     * @return the value of t_order_goods.order_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.order_id
     *
     * @param orderId the value for t_order_goods.order_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.goods_id
     *
     * @return the value of t_order_goods.goods_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.goods_id
     *
     * @param goodsId the value for t_order_goods.goods_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.goods_name
     *
     * @return the value of t_order_goods.goods_name
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.goods_name
     *
     * @param goodsName the value for t_order_goods.goods_name
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.goods_sn
     *
     * @return the value of t_order_goods.goods_sn
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public String getGoodsSn() {
        return goodsSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.goods_sn
     *
     * @param goodsSn the value for t_order_goods.goods_sn
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.goods_num
     *
     * @return the value of t_order_goods.goods_num
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Short getGoodsNum() {
        return goodsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.goods_num
     *
     * @param goodsNum the value for t_order_goods.goods_num
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setGoodsNum(Short goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.market_price
     *
     * @return the value of t_order_goods.market_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.market_price
     *
     * @param marketPrice the value for t_order_goods.market_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.goods_price
     *
     * @return the value of t_order_goods.goods_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.goods_price
     *
     * @param goodsPrice the value for t_order_goods.goods_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.cost_price
     *
     * @return the value of t_order_goods.cost_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.cost_price
     *
     * @param costPrice the value for t_order_goods.cost_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.member_goods_price
     *
     * @return the value of t_order_goods.member_goods_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public BigDecimal getMemberGoodsPrice() {
        return memberGoodsPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.member_goods_price
     *
     * @param memberGoodsPrice the value for t_order_goods.member_goods_price
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setMemberGoodsPrice(BigDecimal memberGoodsPrice) {
        this.memberGoodsPrice = memberGoodsPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.give_integral
     *
     * @return the value of t_order_goods.give_integral
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.give_integral
     *
     * @param giveIntegral the value for t_order_goods.give_integral
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.spec_key
     *
     * @return the value of t_order_goods.spec_key
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public String getSpecKey() {
        return specKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.spec_key
     *
     * @param specKey the value for t_order_goods.spec_key
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setSpecKey(String specKey) {
        this.specKey = specKey == null ? null : specKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.spec_key_name
     *
     * @return the value of t_order_goods.spec_key_name
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public String getSpecKeyName() {
        return specKeyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.spec_key_name
     *
     * @param specKeyName the value for t_order_goods.spec_key_name
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setSpecKeyName(String specKeyName) {
        this.specKeyName = specKeyName == null ? null : specKeyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.bar_code
     *
     * @return the value of t_order_goods.bar_code
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public String getBarCode() {
        return barCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.bar_code
     *
     * @param barCode the value for t_order_goods.bar_code
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.is_comment
     *
     * @return the value of t_order_goods.is_comment
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Byte getIsComment() {
        return isComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.is_comment
     *
     * @param isComment the value for t_order_goods.is_comment
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setIsComment(Byte isComment) {
        this.isComment = isComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.prom_type
     *
     * @return the value of t_order_goods.prom_type
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Byte getPromType() {
        return promType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.prom_type
     *
     * @param promType the value for t_order_goods.prom_type
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setPromType(Byte promType) {
        this.promType = promType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.prom_id
     *
     * @return the value of t_order_goods.prom_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Integer getPromId() {
        return promId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.prom_id
     *
     * @param promId the value for t_order_goods.prom_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.is_send
     *
     * @return the value of t_order_goods.is_send
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Byte getIsSend() {
        return isSend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.is_send
     *
     * @param isSend the value for t_order_goods.is_send
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setIsSend(Byte isSend) {
        this.isSend = isSend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.delivery_id
     *
     * @return the value of t_order_goods.delivery_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public Integer getDeliveryId() {
        return deliveryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.delivery_id
     *
     * @param deliveryId the value for t_order_goods.delivery_id
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_goods.sku
     *
     * @return the value of t_order_goods.sku
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public String getSku() {
        return sku;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_goods.sku
     *
     * @param sku the value for t_order_goods.sku
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_goods
     *
     * @mbg.generated Thu Apr 11 14:20:20 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recId=").append(recId);
        sb.append(", orderId=").append(orderId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsSn=").append(goodsSn);
        sb.append(", goodsNum=").append(goodsNum);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", memberGoodsPrice=").append(memberGoodsPrice);
        sb.append(", giveIntegral=").append(giveIntegral);
        sb.append(", specKey=").append(specKey);
        sb.append(", specKeyName=").append(specKeyName);
        sb.append(", barCode=").append(barCode);
        sb.append(", isComment=").append(isComment);
        sb.append(", promType=").append(promType);
        sb.append(", promId=").append(promId);
        sb.append(", isSend=").append(isSend);
        sb.append(", deliveryId=").append(deliveryId);
        sb.append(", sku=").append(sku);
        sb.append("]");
        return sb.toString();
    }
}