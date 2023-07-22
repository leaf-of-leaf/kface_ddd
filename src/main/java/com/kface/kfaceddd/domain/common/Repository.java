package com.kface.kfaceddd.domain.common;

public interface Repository<KEY, ENTITY> {

    void save(ENTITY entity);

    void removeById(KEY identify);

    ENTITY findById(KEY identify);

}
