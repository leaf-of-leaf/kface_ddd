package com.kface.kfaceddd.application.cqrs.cmd;

public class OrderPriceUpdateCmd {

    private String orderNo;

    private Integer price;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
