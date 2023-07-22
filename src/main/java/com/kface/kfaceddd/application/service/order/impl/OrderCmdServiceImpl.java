package com.kface.kfaceddd.application.service.order.impl;

import com.kface.kfaceddd.application.cqrs.cmd.OrderCreateCmd;
import com.kface.kfaceddd.application.cqrs.cmd.OrderPriceUpdateCmd;
import com.kface.kfaceddd.application.cqrs.cmd.OrderUpdateCmd;
import com.kface.kfaceddd.application.service.order.OrderCmdService;
import com.kface.kfaceddd.common.exception.Assert;
import com.kface.kfaceddd.domain.order.aggregate.Order;
import com.kface.kfaceddd.domain.order.factory.OrderFactory;
import com.kface.kfaceddd.domain.order.repository.OrderRepository;
import com.kface.kfaceddd.domain.order.vo.OrderNoVO;
import com.kface.kfaceddd.domain.order.vo.PriceVO;
import com.kface.kfaceddd.domain.order.vo.UidVO;
import com.kface.kfaceddd.infrastructure.event.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;

@Service
public class OrderCmdServiceImpl implements OrderCmdService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private DomainEventPublisher publisher;

    @Resource
    private OrderFactory orderFactory;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void modify(OrderUpdateCmd orderUpdateCmd) throws ValidationException {
        Order order = orderRepository.find(new OrderNoVO(orderUpdateCmd.getOrderNo()));
        Assert.notNull(order, "order is null when find by " + orderUpdateCmd.getOrderNo());
        order.statusChange(orderUpdateCmd.getStatus());
        orderRepository.save(order);

        // 事件发布
        publisher.publish(order.getEvents());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void create(OrderCreateCmd orderCreateCmd) throws ValidationException {
        // 工厂生成 order
        Order order = orderFactory.newInstance(new PriceVO(orderCreateCmd.getPrice(), orderCreateCmd.getOriginPrice()),
                new UidVO(orderCreateCmd.getUid()));

        orderRepository.save(order);

        // 发布创建事件
        publisher.publish(order.getEvents());
    }

    @Override
    public void modify(OrderPriceUpdateCmd orderPriceUpdateCmd) throws ValidationException {
        Order order = orderRepository.find(new OrderNoVO(orderPriceUpdateCmd.getOrderNo()));
        Assert.notNull(order, "order is null when find by " + orderPriceUpdateCmd.getOrderNo());
        order.priceChange(orderPriceUpdateCmd.getPrice());
        orderRepository.save(order);
        publisher.publish(order.getEvents());
    }
}
