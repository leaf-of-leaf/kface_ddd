package com.kface.kfaceddd.domain.order.repository;

import com.kface.kfaceddd.domain.common.Repository;
import com.kface.kfaceddd.domain.order.aggregate.Order;
import com.kface.kfaceddd.domain.order.vo.OrderNoVO;

public interface OrderRepository extends Repository<String, Order> {

    Order find(OrderNoVO orderNo);

}
