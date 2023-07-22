package com.kface.kfaceddd.domain.order.event;

import com.kface.kfaceddd.client.constant.BizTypeEnum;
import com.kface.kfaceddd.client.constant.EventStatusEnum;
import com.kface.kfaceddd.client.constant.ScopeEnum;
import com.kface.kfaceddd.infrastructure.event.AbstractDomainEvent;

public class OrderCreateEvent extends AbstractDomainEvent<OrderCreateEvent.InnerData> {

    public OrderCreateEvent(Object source, String identify, Integer eventStatus, OrderCreateEvent.InnerData data) {
        super(source, identify, eventStatus, data);
    }

    public OrderCreateEvent(Object source, OrderCreateEvent.InnerData innerData) {
        this(source, null, EventStatusEnum.WAIT_PUBLISH.getIndex(), innerData);
    }

    @Override
    public String getIdentify() {
        return super.getIdentify();
    }

    @Override
    public String getBizType() {
        return BizTypeEnum.ORDER.getBizType();
    }

    @Override
    public Integer getEventStatus() {
        return this.eventStatus;
    }

    @Override
    public String getVersion() {
        return "OrderCreateEventV1";
    }

    @Override
    public Integer getScope() {
        return ScopeEnum.ALL.getIndex();
    }

    public static class InnerData {

        public InnerData(String orderNo) {
            this.orderNo = orderNo;
        }

        String orderNo;

    }
}
