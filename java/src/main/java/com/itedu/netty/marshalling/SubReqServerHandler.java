package com.itedu.netty.marshalling;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@Sharable
class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UserEntity user = (UserEntity) msg;
        System.out.println("服务器端收到:" + user.getUserName() + "---" + user.getId());
        ctx.writeAndFlush(getUser(user.getId()));
    }

    private UserEntity getUser(Integer id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUserName("zhangsan");
        return userEntity;
    }
}
