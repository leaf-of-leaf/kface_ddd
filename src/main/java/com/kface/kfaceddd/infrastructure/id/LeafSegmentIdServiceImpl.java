package com.kface.kfaceddd.infrastructure.id;

import com.kface.kfaceddd.common.exception.BizException;
import com.kface.kfaceddd.domain.id.GlobalIdService;
import com.kface.kfaceddd.infrastructure.dao.id.mapper.LeafSegmentMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component("id:common:service")
public class LeafSegmentIdServiceImpl implements GlobalIdService {

    @Resource
    private LeafSegmentMapper leafSegmentMapper;

    private Map<String, CopyOnWriteArrayList<Long>> idCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void initIdCache() {
        // 解析domain中支持分布式id的聚合根和实体

        // 初始化各个String

    }

    /**
     * 初始化id
     * @param bizType
     */
    @Retryable(value = BizException.class)
    public void initBizIds(String bizType) {
        // 查询

        // 乐观锁更新

        // 更新失败抛出 BizException 进行重试
        
        // 成功则生成ID列表
        Long maxId = 0L;
        Integer segment = 2000;
        for (int i = 0; i < 16; i++) {
            
        }
    }

    @Override
    public String generate(String bizType) {
        CopyOnWriteArrayList<Long> ids = idCache.get(bizType);
        if (CollectionUtils.isEmpty(ids)) {
            initBizIds(bizType);
        }
        return ids.remove(0).toString();
    }

}
