package com.jifenke.lepluslive.groupon.domain.criteria;

import io.swagger.models.auth.In;

/**
 * GrouponRefundCriteria
 * 退款单 - 查询条件
 *
 * @author XF
 * @date 2017/6/22
 */
public class GrouponRefundCriteria {
    private String orderSid;        //  订单编号
    private String productSid;      //  团购ID
    private String productName;     // 产品名称
    private String orderType;       // 订单类型
    private Integer state;          // 状态
    private Integer offset;                 // 页数

    public String getOrderSid() {
        return orderSid;
    }

    public void setOrderSid(String orderSid) {
        this.orderSid = orderSid;
    }

    public String getProductSid() {
        return productSid;
    }

    public void setProductSid(String productSid) {
        this.productSid = productSid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
