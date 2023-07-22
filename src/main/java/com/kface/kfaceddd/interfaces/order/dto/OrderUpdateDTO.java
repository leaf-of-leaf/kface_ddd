package com.kface.kfaceddd.interfaces.order.dto;

public class OrderUpdateDTO {

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

    public OrderUpdateDTO(String orderNo, Integer status) {
        this.orderNo = orderNo;
        this.status = status;
    }

    public OrderUpdateDTO() {
    }
}
