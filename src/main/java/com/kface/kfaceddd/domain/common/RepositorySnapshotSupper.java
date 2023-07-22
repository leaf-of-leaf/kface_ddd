package com.kface.kfaceddd.domain.common;

import com.kface.kfaceddd.common.utils.ObjectTrackingUtil;
import org.javers.core.diff.Diff;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

public abstract class RepositorySnapshotSupper<KEY, ENTITY extends BaseAggregate<KEY>>
        implements Repository<KEY, ENTITY> {

    protected abstract void saveImpl(ENTITY entity);

    protected abstract void removeImpl(KEY identify);

    protected abstract ENTITY findByIdImpl(KEY identify);

    protected abstract void modifyImpl(ENTITY entity, Diff diff);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ENTITY entity) {
        ENTITY snapshot = null;
        if (Objects.nonNull(entity)) {
            snapshot = getSnapshot().getSnapshot(entity.getIdentify());
        }

        if (Objects.isNull(snapshot)) {
            saveImpl(entity);
        } else {
            // diff
            Diff diff = ObjectTrackingUtil.compare(snapshot, entity);
            // 如果是修改，按照资源库的方法必定是先find再modify的，因此可以通过是否有缓存来判断
            modifyImpl(entity, diff);
        }

        // remove而不是merge,如果只是本机merge则会导致多个服务间不一致
        // 同时加入分布式缓存则会导致复杂度高
        getSnapshot().removeSnapshot(entity.getIdentify());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(KEY identify) {
        getSnapshot().removeSnapshot(identify);
        removeImpl(identify);
    }

    @Override
    public ENTITY findById(KEY identify) {
        ENTITY entity = findByIdImpl(identify);
        getSnapshot().saveSnapshot(entity);
        return entity;
    }

    // 获取holder
    protected AggregateRootSnapshot<KEY, ENTITY> getSnapshot() {
        return AggregateRootSnapshotHolder.getInstance();
    }
}