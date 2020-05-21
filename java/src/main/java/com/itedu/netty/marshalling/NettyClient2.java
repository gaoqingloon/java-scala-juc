package com.itedu.netty.marshalling;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

class SubReqClientHandler extends ChannelHandlerAdapter {

    /**
     * 客户端与服务器端进行连接 可以发送数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务器端进行连接成功...");
        ctx.write(getUser(1));
        ctx.flush();
    }

    private UserEntity getUser(Integer id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUserName("zhangsan");
        return userEntity;
    }

    /**
     * 当Channel上的某个读操作完成时被调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 处理过程中ChannelPipeline中发生错误时被调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UserEntity user = (UserEntity) msg;
        System.out.println("客户端端收到:" + user.getUserName() + "---" + user.getId());
        ctx.writeAndFlush(getUser(user.getId()));
    }
}

public class NettyClient2 {
    public static void main(String[] args) {
        try {
            // 配置客户端NIO线程组
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                                ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                                ch.pipeline().addLast(new SubReqClientHandler());
                            }
                        });
                // 发起异步连接操作
                ChannelFuture f = b.connect("127.0.0.1", 8086).sync();
                // f.channel().write(subReq(1));
                // 当代客户端链路关闭
                f.channel().closeFuture().sync();
            } finally {
                // 优雅退出，释放NIO线程组
                group.shutdownGracefully();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
