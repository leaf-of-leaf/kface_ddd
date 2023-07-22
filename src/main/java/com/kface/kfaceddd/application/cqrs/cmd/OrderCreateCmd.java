package com.kface.kfaceddd.application.cqrs.cmd;

public class OrderCreateCmd {

    private String uid;

    private Integer price;

    private Integer originPrice;

    public String getUid() {
        return uid;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getOriginPrice() {
        return originPrice;
    }
}
