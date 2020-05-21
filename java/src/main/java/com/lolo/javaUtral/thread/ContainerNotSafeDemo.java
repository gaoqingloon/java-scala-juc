package com.lolo.javaUtral.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全的问题
 * ArrayList
 * HashSet
 * HashMap
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        //listNotSafe();
        //setNotSafe();
        mapNotSafe();
    }

    public static void mapNotSafe() {
        //Map<String, String> map = new HashMap<>();  // java.util.ConcurrentModificationException
        //Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        //Set<String> set = new HashSet<>();  // java.util.ConcurrentModificationException
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        /*
        HashSet 底层是HashMap，HashMap添加KV，HashSet添加K（V默认是PRESENT new Object()）
        private transient HashMap<E,Object> map;
        // Dummy value to associate with an Object in the backing Map
        private static final Object PRESENT = new Object();
         */

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    public static void listNotSafe() {

        // 1. 单线程
//        List<String> list = Arrays.asList("a", "b", "c");
//        list.forEach(System.out::println);

//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        for (String ele : list) {
//            System.out.println(ele);
//        }

        // 2. 多线程问题
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(list);
//            }, String.valueOf(i)).start();
//        }
//        // java.util.ConcurrentModificationException 并发修改异常

        // 3. 解决方案
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /**
         * 不要只是会用，会用只不过是一个API调用工程师
         * 底层原理？
         *
         * 方法论：（经常出的错）
         * 1. 故障现象
         *      java.util.ConcurrentModificationException
         *
         * 2. 导致原因
         *      并发争抢修改导致，套考我们的花名册签名情况。
         *      一个人正在写入，另外一个同学过来抢夺，导致数据不一致异常。并发修改异常。
         *
         * 3. 解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>());
         *      3.3 new CopyOnWriteArrayList<>();
         *
         * 4. 优化建议（同样的错误不犯第2次）
         */
    }
}

/**
 * 笔记
 写时复制
 CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object进copy，
 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元系，添加完元素之后，
 再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以和CopyOnWrite容器进行并发的读，
 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器

 public boolean add(E e) {
     final ReentrantLock lock = this.lock;
     lock.lock();
     try {
         Object[] elements = getArray();
         int len = elements.length;
         Object[] newElements = Arrays.copyOf(elements, len + 1);
         newElements[len] = e;
         setArray(newElements);
         return true;
     } finally {
         lock.unlock();
     }
 }
 */
