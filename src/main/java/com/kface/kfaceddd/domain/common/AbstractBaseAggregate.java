package com.kface.kfaceddd.domain.common;

import com.kface.kfaceddd.client.constant.DdlTypeEnum;
import com.kface.kfaceddd.infrastructure.event.DomainEvent;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBaseAggregate<KEY> implements BaseAggregate<KEY> {

    @DiffIgnore
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    @DiffIgnore
    protected DdlTypeEnum ddlTypeEnum = DdlTypeEnum.UNDEFINED;

    @Override
    public void registerEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    @Override
    public List<DomainEvent> getEvents() {
        return domainEvents;
    }

    @Override
    public abstract KEY getIdentify();

}
