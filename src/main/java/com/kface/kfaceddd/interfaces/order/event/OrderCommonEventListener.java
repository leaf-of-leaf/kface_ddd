package com.kface.kfaceddd.interfaces.order.event;

import com.kface.kfaceddd.domain.order.event.OrderCreateEvent;
import com.kface.kfaceddd.domain.order.event.OrderStatusChangeEvent;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderCommonEventListener {

    @Resource
    private AmqpTemplate amqpTemplate;

    @EventListener
    public void modifyEvent(OrderStatusChangeEvent event) {

    }

    @EventListener
    public void modifyOrderCache(OrderCreateEvent event) {
        // cache operate
    }

    @EventListener
    public void vipAddValidDate(OrderCreateEvent event) {
        String parentId = event.getIdentify();
        // 事件落地
        amqpTemplate.convertAndSend(event);
    }

    @EventListener
    public void addScore(OrderCreateEvent event) {
        amqpTemplate.convertAndSend(event);
    }

}
