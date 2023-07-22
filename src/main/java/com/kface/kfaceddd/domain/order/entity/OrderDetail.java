package com.kface.kfaceddd.domain.order.entity;

import com.kface.kfaceddd.domain.common.Entity;
import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.TypeName;

@TypeName("orderDetail")
public class OrderDetail implements Entity<String> {

    Long id;

    @Id
    String orderNo;

    Integer status;

    public OrderDetail(Long id) {
        this.id = id;
    }

    public OrderDetail(Long id, String orderNo) {
        this.id = id;
        this.orderNo = orderNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String getIdentify() {
        return orderNo;
    }
}
