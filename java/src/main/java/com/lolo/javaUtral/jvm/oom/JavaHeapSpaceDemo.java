package com.lolo.javaUtral.jvm.oom;

import java.util.Random;

/**
 * new 大对象 撑爆堆空间
 * -Xms10m -Xmx10m
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {

        byte[] bytes = new byte[80 * 1024 * 1024];  // 80MB
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space

        String str = "study";
        while (true) {
            str += str + new Random().nextInt(1111111) + new Random().nextInt(2222222);
            str.intern();
        }
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
