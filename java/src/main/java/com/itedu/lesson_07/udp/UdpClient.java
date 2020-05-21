package com.itedu.lesson_07.udp;

import java.io.IOException;
import java.net.*;

public class UdpClient {

    public static void main(String[] args) throws IOException {

        System.out.println("udp 客户端发送数据...");

        DatagramSocket ds = new DatagramSocket();

        String content = "客户端发送数据...";
        byte[] con = content.getBytes();
//        DatagramPacket datagramPacket = new DatagramPacket(content.getBytes(), content.length(),
//                InetAddress.getByAddress("192.168.1.100".getBytes()), 8080);
        DatagramPacket dp = new DatagramPacket(con, con.length, InetAddress.getByName("192.168.1.100"), 8080);

        ds.send(dp);

        ds.close();
    }
}
