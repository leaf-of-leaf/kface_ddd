package com.kface.kfaceddd.domain.order.vo;

import org.apache.commons.lang3.StringUtils;

public class UidVO {

    private String uid;

    public UidVO(String uid) {
        if (StringUtils.isBlank(uid)) {
            System.out.println("uid is blank");
        }
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
}
