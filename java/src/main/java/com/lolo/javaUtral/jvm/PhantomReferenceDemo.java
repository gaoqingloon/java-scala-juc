package com.lolo.javaUtral.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * java提供4种引用类型，在垃圾回收的时候，都有自己各自的特点。
 * ReferenceQueue是用来合引用工作的，没有 ReferenceQueue一样可以运行。
 *
 * 创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列，
 * 如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对的内存被回收之前采取必要的行动
 * 这相当于是一种通知机制。
 *
 * 当关联的引用队列中有数据的时候，意味若引用指向的堆内存中的对象被回收。通过这种方式，JvM允许我们在对象被销段后，
 * 做一些我们自己想做的享情。
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {

        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("================");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        /*
        java.lang.Object@12a3a380
        null
        null
        ================
        null
        null
        java.lang.ref.PhantomReference@29453f44
         */
    }
}
