package com.kface.kfaceddd.infrastructure.event;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public abstract class AbstractDomainEvent<T> extends ApplicationEvent implements DomainEvent {

    protected String identify;

    protected Integer eventStatus;

    protected T data;

    protected Integer scope;

    @Override
    public Integer getScope() {
        return this.scope;
    }

    @Override
    public String getIdentify() {
        if (StringUtils.isBlank(this.identify)) {
            this.identify = UUID.randomUUID().toString();
            return identify;
        }
        return this.identify;
    }

    public AbstractDomainEvent(Object source, String identify, Integer eventStatus, T data) {
        super(source);
        this.identify = identify;
        this.eventStatus = eventStatus;
        this.data = data;
    }
}
