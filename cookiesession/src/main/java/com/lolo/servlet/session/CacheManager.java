package com.lolo.servlet.session;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 提供缓存api
 * 缓存框架的实现原理
 */
public class CacheManager {

    // 存放缓存数据
    private Map<String, Cache> cacheMap = new HashMap<>();

    /**
     * put
     */
    public void put(String key, Object value) {
        put(key, value, null);
    }

    public synchronized void put(String key, Object value, Long timeout) {
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setValue(value);
        if (timeout != null) {
            // 保存的是 整个毫秒数
            cache.setTimeout(System.currentTimeMillis() + timeout);
        }
        cacheMap.put(key, cache);
    }

    /**
     * 删除api
     */
    public synchronized void del(String key) {
        cacheMap.remove(key);
    }

    /**
     * 使用key 查询缓存
     */
    public synchronized Object get(String key) {
        Cache cache = cacheMap.get(key);
        if (cache != null) {
            return cache.getValue();
        }
        return null;
    }

    /**
     * 删除key
     */
    public synchronized void remove(String key) {
        System.out.println("key: " + key + "删除成功！");
        cacheMap.remove(key);
    }

    /**
     * 定义检查，删除有限期的值
     */
    public synchronized void checkValidityData() {
        for (String key : cacheMap.keySet()) {
            Cache cache = cacheMap.get(key);
            if (cache == null) {
                break;
            }
            // 检查有效期，毫秒数 5+5000 5005
            Long timeout = cache.getTimeout();
            // 计算时间差，当前的毫秒数
            long currentTimeMillis = System.currentTimeMillis();
            // 说明已经过期
            if ((currentTimeMillis - timeout) > 0) {
                remove(key);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        CacheManager cacheManager = new CacheManager();
        cacheManager.put("userName", "123", 5000L);
        System.out.println("保存成功！");

        // 开启一个线程定时刷新
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                cacheManager.checkValidityData();
            }
        }, 5000, TimeUnit.MILLISECONDS);

        Thread.sleep(6000);

        String userName = (String) cacheManager.get("userName");
        System.out.println("userName: " + userName);

        // 开启一个线程，检查有效期

    }
}
