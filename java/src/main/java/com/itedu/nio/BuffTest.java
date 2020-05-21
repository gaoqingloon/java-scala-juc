package com.itedu.nio;

import java.nio.ByteBuffer;

/**
 * (缓冲区)buffer 用于NIO存储数据 支持多种不同的数据类型
 *  1. byteBuffer
 *  2. charBuffer
 *  3. shortBuffer
 *  4. IntBuffer
 *  5. LongBuffer
 *  6. FloatBuffer
 *  7. DoubleBuffer
 *
 * 一、上述缓冲区管理的方式 几乎通过allocate（） 获取缓冲区
 * 二、缓冲区核心的方法 put 存入数据到缓冲区 get  获取缓冲区数据 flip 开启读模式
 * 三、缓冲区四个核心属性
 *  capacity: 缓冲区最大容量，一旦声明不能改变
 *  limit: 界面(缓冲区可以操作的数据大小) limit后面的数据不能读写
 *  position: 缓冲区正在操作的位置
 */
public class BuffTest {

    public static void main(String[] args) {

        // 1.指定缓冲区大小1024
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 2.向缓冲区存放5个数据
        buf.put("abcd1".getBytes());
        System.out.println("--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 3.开启读模式
        buf.flip();
        System.out.println("----------开启读模式...----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));

        // 4.开启重复读模式
        System.out.println("----------重复读模式...----------");
        buf.rewind();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        byte[] bytes2 = new byte[buf.limit()];
        buf.get(bytes2);
        System.out.println(new String(bytes2, 0, bytes2.length));

        // 5.clean 清空缓冲区  数据依然存在,只不过数据被遗忘
        System.out.println("----------清空缓冲区...----------");
        buf.clear();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println((char) buf.get());
    }
}
/*
--------------------
0
1024
1024
--------------------
5
1024
1024
----------开启读模式...----------
0
5
1024
abcd1
----------重复读模式...----------
0
5
1024
abcd1
----------清空缓冲区...----------
0
1024
1024
a
 */
