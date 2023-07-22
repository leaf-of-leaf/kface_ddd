package com.kface.kfaceddd.client.constant;

public enum BizTypeEnum {

    ORDER(1, "0001", "order");

    int index;

    String bizType;

    String bizName;

    BizTypeEnum(int index, String bizType, String bizName) {
        this.index = index;
        this.bizType = bizType;
        this.bizName = bizName;
    }

    public int getIndex() {
        return index;
    }

    public String getBizType() {
        return bizType;
    }

    public String getBizName() {
        return bizName;
    }
}
