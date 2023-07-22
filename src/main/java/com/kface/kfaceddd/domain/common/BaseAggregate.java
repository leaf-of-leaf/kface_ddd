package com.kface.kfaceddd.domain.common;

import com.kface.kfaceddd.infrastructure.event.DomainEvent;

import java.util.List;

public interface BaseAggregate<KEY> extends Entity<KEY> {

    void registerEvent(DomainEvent event);

    List<DomainEvent> getEvents();

}