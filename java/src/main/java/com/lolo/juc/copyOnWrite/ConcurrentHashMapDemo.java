package com.lolo.juc.copyOnWrite;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        /**
         public V put(K key, V value) {
            return putVal(key, value, false);  // 比较复杂
         }
         */
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 50; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 6));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
