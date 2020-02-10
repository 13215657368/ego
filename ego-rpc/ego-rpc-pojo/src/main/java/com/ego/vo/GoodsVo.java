package com.ego.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(indexName = "ego", type = "goods")
public class GoodsVo implements Serializable {

    /*
        属性的名称一定要和索引库的名称一致
     */
    @Id
    @Field(index = FieldIndex.not_analyzed, type = FieldType.Integer)
    private Integer goodsId;

    @Field(type = FieldType.String, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String goodsName;

    private String goodsHlName;

    @Field(type = FieldType.Double, searchAnalyzer = "ik", analyzer = "ik")
    private BigDecimal marketPrice;

    @Field(type = FieldType.String, searchAnalyzer = "ik", analyzer = "ik")
    private String originalImg;

    private Integer goodsNum;

    private Date addTime;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getOriginalImg() {
        return originalImg;
    }

    public void setOriginalImg(String originalImg) {
        this.originalImg = originalImg;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getGoodsHlName() {
        return goodsHlName;
    }

    public void setGoodsHlName(String goodsHlName) {
        this.goodsHlName = goodsHlName;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsHlName='" + goodsHlName + '\'' +
                ", marketPrice=" + marketPrice +
                ", originalImg='" + originalImg + '\'' +
                ", goodsNum=" + goodsNum +
                ", addTime=" + addTime +
                '}';
    }
}
