package com.kface.kfaceddd.infrastructure.converter.order;

import com.kface.kfaceddd.domain.order.aggregate.Order;
import com.kface.kfaceddd.domain.order.entity.OrderDetail;
import com.kface.kfaceddd.infrastructure.dao.order.OrderDetailPO;
import com.kface.kfaceddd.infrastructure.dao.order.OrderPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderConverter {

    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    @Mappings({
            @Mapping(source = "order.priceVO.price", target = "price"),
            @Mapping(source = "order.priceVO.originPrice", target = "originPrice")
    })
    OrderPO toOrderPO(Order order);

    OrderDetailPO toOrderDetailPO(OrderDetail detail);

}
