package com.kface.kfaceddd.common.constant;

import java.util.HashSet;
import java.util.Set;

public class OrderConstant {

    // 0=未支付，1=已失效，2=已支付定金，3=已支付总额，5=部分退款，6=全部退款
    public interface OrderStatus {
        int UNPAID = 0;

        int INVALID = 1;

        int PAID_DEPOSIT = 2;

        int PAID_TOTAL = 3;

        int PARTIAL_REFUND = 5;

        int FULL_REFUND = 6;
    }

    public static Set<Integer> getValidOrderStatus() {
        return new HashSet<Integer>() {
            {
                add(OrderStatus.INVALID);
                add(OrderStatus.UNPAID);
                add(OrderStatus.PAID_DEPOSIT);
            }
        };
    }

}
