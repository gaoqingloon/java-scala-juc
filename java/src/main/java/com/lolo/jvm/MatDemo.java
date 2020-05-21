package com.lolo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
 * 当发生堆内存溢出的时候，记录下来
 * IDEA 需要安装 JProfiler 插件
 */
public class MatDemo {

    byte[] byteArray = new byte[1 * 1024 * 1024];  // 1MB 的空间

    public static void main(String[] args) {

        List<MatDemo> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new MatDemo());  // 每次装进入1M
                count = count + 1;
            }
        } catch (Throwable e) {
            System.out.println("********count: " + count);
            e.printStackTrace();
        }
    }
}
/*
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid7524.hprof ...
Heap dump file created [7717649 bytes in 0.016 secs]
********count: 6
java.lang.OutOfMemoryError: Java heap space
	at com.lolo.jvm.MatDemo.<init>(MatDemo.java:12)
	at com.lolo.jvm.MatDemo.main(MatDemo.java:20)
 */