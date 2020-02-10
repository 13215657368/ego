package com.ego.result;

import com.ego.vo.GoodsVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车返回对象
 */
public class CartResult implements Serializable {
    private List<GoodsVo> goodsVoList;
    private BigDecimal totalPrice;

    public List<GoodsVo> getGoodsVoList() {
        return goodsVoList;
    }

    public void setGoodsVoList(List<GoodsVo> goodsVoList) {
        this.goodsVoList = goodsVoList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartResult{" +
                "goodsVoList=" + goodsVoList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
