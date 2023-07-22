package com.kface.kfaceddd.infrastructure.thead.pool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class DomainEventThreadPool {

    @Bean("domainEventThreadPool")
    public ExecutorService getDomainEventThreadPool() {
        return new ThreadPoolExecutor(1, 1, Long.MAX_VALUE,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(Integer.MAX_VALUE), r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("domain-event-thread");
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(t.getThreadGroup().getName() + "-" + t.getName() + "-" + e.getMessage());
                }
            });
            return thread;
        }, new ThreadPoolExecutor.AbortPolicy());
    }

}
