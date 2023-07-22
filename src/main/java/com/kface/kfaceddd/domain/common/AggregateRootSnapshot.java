package com.kface.kfaceddd.domain.common;

public interface AggregateRootSnapshot<KEY, ENTITY extends BaseAggregate<KEY>>{

    /**
     * 保存快照
     */
    void saveSnapshot(ENTITY aggregateRoot);

    /**
     * 获取快照
     */
    ENTITY getSnapshot(KEY identify);

    /**
     * 移除快照
     */
    void removeSnapshot(KEY identify);

}
