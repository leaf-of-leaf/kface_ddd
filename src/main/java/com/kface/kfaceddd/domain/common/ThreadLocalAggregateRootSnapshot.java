package com.kface.kfaceddd.domain.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalAggregateRootSnapshot<KEY, ENTITY extends BaseAggregate<KEY>>
        implements AggregateRootSnapshot<KEY, ENTITY> {

    private final ThreadLocal<Map<KEY, ENTITY>> AGGREGATE_CONTEXT = ThreadLocal.withInitial(ConcurrentHashMap::new);

    @Override
    public void saveSnapshot(ENTITY aggregateRoot) {
        AGGREGATE_CONTEXT.get().put(aggregateRoot.getIdentify(), aggregateRoot);
    }

    @Override
    public ENTITY getSnapshot(KEY identify) {
        Map<KEY, ENTITY> map = AGGREGATE_CONTEXT.get();
        return map.get(identify);
    }

    @Override
    public void removeSnapshot(KEY identify) {
        AGGREGATE_CONTEXT.get().remove(identify);
    }
}
