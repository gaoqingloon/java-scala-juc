package com.lolo.javaUtral.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * GC回收时间过长时会抛出 OutOfMemoryError，过长的定义是，超过8%的时间用来做GC并且回收了不到2的堆内存
 * 读续多执6C都只回收了不到2%的极端情况下才会抛出。假如不抛出 GC overhead limit错误会发生什么情况呢?
 * 那就是GC清理的这么点内存很快会再次填满，迫使GC再次执行，这样就形成恶性循环
 * CPU使用率一直是100%，而GC却没有在何成果
 */
public class GCOverheadDemo {
    public static void main(String[] args) {

        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();  // java.lang.OutOfMemoryError: GC overhead limit exceeded
        } finally {
            System.out.println("******i: " + i);  // ******i: 145715
        }
    }
}
