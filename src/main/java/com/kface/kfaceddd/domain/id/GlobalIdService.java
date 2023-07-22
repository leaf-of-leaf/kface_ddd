package com.kface.kfaceddd.domain.id;

/**
 * 分布式唯一ID
 */
public interface GlobalIdService {

    String generate(String bizType);

}
