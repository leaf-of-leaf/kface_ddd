package com.kface.kfaceddd.client.constant;

public enum ScopeEnum {
    APPLICATION(1, "应用内事件"),
    INTER_SERVICE(2, "服务间事件"),
    ALL(3, "all"),;

    int index;

    String desc;

    ScopeEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }
}
