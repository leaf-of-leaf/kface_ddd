package com.kface.kfaceddd.domain.order.event;

import com.kface.kfaceddd.client.constant.BizTypeEnum;
import com.kface.kfaceddd.client.constant.EventStatusEnum;
import com.kface.kfaceddd.client.constant.ScopeEnum;
import com.kface.kfaceddd.domain.order.aggregate.Order;
import com.kface.kfaceddd.infrastructure.event.AbstractDomainEvent;

public class OrderStatusChangeEvent extends AbstractDomainEvent<OrderStatusChangeEvent.InnerData> {

    public OrderStatusChangeEvent(Object source, String identify, Integer eventStatus, InnerData data) {
        super(source, identify, eventStatus, data);
    }

    public OrderStatusChangeEvent(Order source, InnerData innerData) {
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
        return EventStatusEnum.WAIT_PUBLISH.getIndex();
    }

    @Override
    public String getVersion() {
        return "OrderStatusChangeEventV1";
    }

    @Override
    public Integer getScope() {
        return ScopeEnum.ALL.getIndex();
    }

    public static class InnerData {

        public InnerData(Integer toStatus, String orderNo) {
            this.toStatus = toStatus;
            this.orderNo = orderNo;
        }

        Integer toStatus;

        String orderNo;

    }
}
