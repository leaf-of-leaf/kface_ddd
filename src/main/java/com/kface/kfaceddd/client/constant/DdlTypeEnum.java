package com.kface.kfaceddd.client.constant;

public enum DdlTypeEnum {
    UNDEFINED(0, "未定义"),
    INSERT(1, "新增"),
    UPDATE(2, "更新"),
    DELETE(3, "删除");

    private int type;

    private String desc;

    DdlTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
