package com.lolo.javaUtral.jvm.oom;

import java.nio.ByteBuffer;

/**
 * 配置参数
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 故障现象
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *
 * 导致原因：
 * 写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道(Channel)与缓冲区(Buffer)的I/O方式，
 * 它可以使用Native函数库直接分配堆外内存，然后过一个存储在Java里面的 DirectByteBuffer对象作为这换内存的引用进行操作
 * 这样能在一些场景中显著提高性能，因为避免了在Java雄和 Native中来回复制数据。
 *
 * ByteBuffer.allocate(capability)第一种方式是分配JVM内存，属GC管范围，由于需要拷贝所以速度相对较慢
 * ByteBuffer.allocateDirect(capability)第一种方式是分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对较快
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执GC， DirectByteBuffer对象们就不会被回收，
 * 这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出OutOfMemoryError，那程序就直接崩溃了。
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("配置的maxDirectMemory: " + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        // 配置的maxDirectMemory: 3628.5MB

        Thread.sleep(3000);

        // -XX:MaxDirectMemorySize=5m     配置为5MB，但实际使用6MB，故意使坏
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
        // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
    }
}
