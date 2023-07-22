package com.kface.kfaceddd.infrastructure.event;

public interface DomainEvent {

    String getIdentify();

    String getVersion();

    String getBizType();

    Integer getEventStatus();

    Integer getScope();

}
