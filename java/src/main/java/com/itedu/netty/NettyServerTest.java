package com.itedu.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerHandler extends SimpleChannelHandler {

    /**
     * 通道关闭的时候触发
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
    }

    /**
     * 必须是连接已经建立,关闭通道的时候才会触发.
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println("channelDisconnected");
    }

    /**
     * 捕获异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exceptionCaught");
    }

    /**
     * 接受消息
     */
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
  		// System.out.println("messageReceived");
        System.out.println("服务器端收到客户端消息:" + e.getMessage());
        // 回复内容
        ctx.getChannel().write("好的");
    }
}

// netty 服务器端
public class NettyServerTest {

    public static void main(String[] args) {
        // 创建服务类对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 创建两个线程池 分别为监听监听端口 ，nio监听
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        // 设置工程 并把两个线程池加入中
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boos, worker));
        // 设置管道工厂
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                // 将数据转换为string类型
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("serverHandler", new ServerHandler());
                return pipeline;
            }
        });
        // 绑定端口号
        serverBootstrap.bind(new InetSocketAddress(9090));
        System.out.println("netty server启动....");
    }
}
