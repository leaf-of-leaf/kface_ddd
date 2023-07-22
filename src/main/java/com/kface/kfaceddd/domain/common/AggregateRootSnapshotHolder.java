package com.kface.kfaceddd.domain.common;

import java.util.Objects;

@SuppressWarnings("all")
public class AggregateRootSnapshotHolder {

    private static AggregateRootSnapshot INSTANCE = null;

    private static final Object LOCK = new Object();

    public static <KEY, ENTITY extends BaseAggregate<KEY>> AggregateRootSnapshot<KEY, ENTITY> getInstance() {
        if (Objects.isNull(INSTANCE)) {
            synchronized (LOCK) {
                if (Objects.isNull(INSTANCE)) {
                    return (INSTANCE = new ThreadLocalAggregateRootSnapshot<>());
                }
            }
        }
        return INSTANCE;
    }

}
