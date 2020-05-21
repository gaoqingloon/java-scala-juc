package com.itedu.lesson_07.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * socket udp 服务器端
 * udp 是面向无连接，协议不可靠
 * 2019-7-4 19:39:41
 */
public class UdpServer {

    public static void main(String[] args) throws IOException {

        System.out.println("udp 服务器端启动...");

        int port = 8080;
        DatagramSocket ds = new DatagramSocket(port);

        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        // 等待客户端进行发送内容，如果客户端不发送内容，一直等待，阻塞的效果
        ds.receive(packet);

        String content = new String(packet.getData(), 0, packet.getLength());
        System.out.println("客户端发送的数据是：" + content);
        ds.close();
    }
}
