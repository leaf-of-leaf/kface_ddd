package com.kface.kfaceddd.client.constant;

import java.util.Objects;

public class CommonConstant {

    public static boolean checkIsYes(Integer status) {
        return Objects.nonNull(status) && status == 1;
    }

    public static boolean checkIsNo(Integer status) {
        return Objects.nonNull(status) && status == 0;
    }

}
