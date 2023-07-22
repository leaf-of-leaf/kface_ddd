package com.kface.kfaceddd.domain.order.vo;

import java.util.Objects;

public class PriceVO {

    private Integer price;

    private Integer originPrice;

    public PriceVO(Integer price, Integer originPrice) {
        if (Objects.isNull(price)) {
            System.out.println("price is null");
        }
        if (Objects.isNull(originPrice)) {
            System.out.println("originPrice is null");
        }
        if (price > originPrice) {
            System.out.println("price ge originPrice");
        }
        this.price = price;
        this.originPrice = originPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getOriginPrice() {
        return originPrice;
    }
}
