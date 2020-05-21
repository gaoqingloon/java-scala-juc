package com.itedu.lesson_07.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp 服务器端
 *
 * Exception in thread "main" java.net.BindException: Address already in use: JVM_Bind
 */
public class TcpServer {

    public static void main(String[] args) throws IOException {

        System.out.println("socket tcp 启动...");
        ServerSocket socket = new ServerSocket(8080);

        // 等待客户端连接，阻塞状态
        Socket accept = socket.accept();

        InputStream inputStream = accept.getInputStream();
        byte[] buf = new byte[1024];
        int len = inputStream.read(buf);
        String content = new String(buf, 0, len);
        System.out.println("content: " + content);

        socket.close();
    }
}
