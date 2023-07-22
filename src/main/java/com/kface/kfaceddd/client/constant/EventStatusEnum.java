package com.kface.kfaceddd.client.constant;

public enum EventStatusEnum {

    WAIT_PUBLISH(1, "待发布"),
    PUBLISHED(2, "已发布"),
    FINISHED(3, "已完成")
    ;

    int index;

    String desc;

    EventStatusEnum(int index, String desc) {
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
