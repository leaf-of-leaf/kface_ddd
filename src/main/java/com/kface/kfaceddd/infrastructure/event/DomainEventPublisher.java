package com.kface.kfaceddd.infrastructure.event;

import com.kface.kfaceddd.client.constant.ScopeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * 进行持久化以及消息投递
 */
@Component
public class DomainEventPublisher {

    @Resource
    private ApplicationEventPublisher springPublisher;

    @Resource
    @Qualifier("domainEventThreadPool")
    private ExecutorService domainEventThreadPool;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void publish(List<DomainEvent> events) {
        if (CollectionUtils.isEmpty(events)) {
            return;
        }

        // 父事件存储
        List<DomainEvent> interServiceEvent = new ArrayList<>();
        for (DomainEvent event : events) {
            if (!Integer.valueOf(ScopeEnum.APPLICATION.getIndex()).equals(event.getScope())) {
                interServiceEvent.add(event);
            }
        }
        if (CollectionUtils.isNotEmpty(interServiceEvent)) {
            System.out.println("父事件存储");
            // eventDao.insertBatch(scopeMap.getOrDefault(2, Collections.emptyList()));
        }

        for (DomainEvent event : events) {
            domainEventThreadPool.execute(() -> {
                springPublisher.publishEvent(event);
            });
        }
    }

}
