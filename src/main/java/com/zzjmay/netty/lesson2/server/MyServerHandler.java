package com.zzjmay.netty.lesson2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * 传输的是字符串
 * Created by zzjmay on 2019/3/14.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {

        //channelHandlerContext netty的上下文
        //查看远程客户端和响应信息
        System.out.println(channelHandlerContext.channel().remoteAddress()+","+msg);

        channelHandlerContext.writeAndFlush("from server"+ UUID.randomUUID());


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
