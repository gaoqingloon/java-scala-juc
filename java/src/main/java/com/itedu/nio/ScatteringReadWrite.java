package com.itedu.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatteringReadWrite {
    public static void main(String[] args) throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("test.txt", "rw");
        // 1.获取通道
        FileChannel channel = raf1.getChannel();
        // 2.分配指定大小的指定缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        // 3.分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel.read(bufs);
        for (ByteBuffer byteBuffer : bufs) {
            // 切换为读取模式
            byteBuffer.flip();
        }
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("------------------分算读取线分割--------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));
        // 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);
    }
}
