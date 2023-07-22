package com.kface.kfaceddd.domain.order.vo;

import javax.xml.bind.ValidationException;
import java.util.Objects;

public class OrderNoVO {

    String orderNo;

    public OrderNoVO(String orderNo) throws ValidationException {
        if (Objects.isNull(orderNo) || "".equals(orderNo)) {
            throw new ValidationException("orderNo is null");
        }
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }
}
