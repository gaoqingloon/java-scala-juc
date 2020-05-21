package com.lolo.juc.copyOnWrite;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetDemo {

    public static void main(String[] args) {

        /**
            public CopyOnWriteArraySet() {
                al = new CopyOnWriteArrayList<E>();
            }
         */
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 50; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
