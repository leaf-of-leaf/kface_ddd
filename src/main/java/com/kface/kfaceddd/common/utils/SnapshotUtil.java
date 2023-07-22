package com.kface.kfaceddd.common.utils;

import com.esotericsoftware.kryo.Kryo;
import com.kface.kfaceddd.domain.order.aggregate.Order;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class SnapshotUtil {

    static private final ConcurrentHashMap<Class<?>, Object> concurrentHashMap = new ConcurrentHashMap<>();

    static private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.setReferences(false);
        return kryo;
    });

    public static <T> T newSnapshot(T obj) {
        Class<?> aClass = obj.getClass();
        Kryo kryo = kryoThreadLocal.get();
        if (!concurrentHashMap.containsKey(aClass)) {
            kryo.register(aClass);
            concurrentHashMap.put(aClass, new Object());
        }
        return kryo.copy(obj);
    }

    /**
     * @param obj
     * @return
     * @param <T>
     */
    public static <T> T newSnapshot(T obj, Function<T, T> function) {
        return function.apply(obj);
    }

    public static void main(String[] args) {
        Kryo kryo = kryoThreadLocal.get();
        StopWatch stopWatch = new StopWatch();
        Order order = new Order("1");
        ArrayList<Object> objects = new ArrayList<>();
        stopWatch.start("kryo");
        for (int i = 0; i < 10000; i++) {
            Order order1 = kryo.copy(order);
            objects.add(order1);
        }
        stopWatch.stop();
        ArrayList<Object> objects2 = new ArrayList<>();
        kryo.register(Order.class);

        stopWatch.start("kryo");
        for (int i = 0; i < 10000; i++) {
            Order order1 = kryo.copy(order);
            objects2.add(order1);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

}
