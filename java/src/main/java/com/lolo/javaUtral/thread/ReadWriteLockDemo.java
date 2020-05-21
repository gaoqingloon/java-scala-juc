package com.lolo.javaUtral.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 资源类
 * 缓存（读、写、清空）
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    //private Lock lock = new ReentrantLock();  // 太重 并且不能并发读
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入: " + key);
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 正在完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {

        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void clearMap() {
        map.clear();
    }

}

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源类，就不应该再有其它线程可以对该资源进行读或写
 *
 * 小总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 *
 *      写操作：原子+独占, 整个过程必须是一个完整的统一体，中间不许被分割，被打断
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                myCache.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                myCache.get(finalI + "");
            }, String.valueOf(i)).start();
        }
    }
}
/*
0	 正在写入: 0
1	 正在写入: 1
3	 正在写入: 3
4	 正在写入: 4
2	 正在写入: 2
0	 正在读取
1	 正在读取
2	 正在读取
3	 正在读取
4	 正在读取
2	 正在完成
0	 正在完成
4	 正在完成
3	 正在完成
1	 正在完成
0	 读取完成: 0
1	 读取完成: null
3	 读取完成: null
2	 读取完成: 2
4	 读取完成: 4
 */
/* 读写分离：严格控制写，读可并发
0	 正在写入: 0
0	 正在完成
1	 正在写入: 1
1	 正在完成
2	 正在写入: 2
2	 正在完成
4	 正在写入: 4
4	 正在完成
3	 正在写入: 3
3	 正在完成
0	 正在读取
1	 正在读取
2	 正在读取
3	 正在读取
4	 正在读取
0	 读取完成: 0
3	 读取完成: 3
4	 读取完成: 4
1	 读取完成: 1
2	 读取完成: 2
 */
