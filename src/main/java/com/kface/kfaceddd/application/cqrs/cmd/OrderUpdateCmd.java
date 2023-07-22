package com.kface.kfaceddd.application.cqrs.cmd;

public class OrderUpdateCmd {

    private String orderNo;

    private Integer status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
