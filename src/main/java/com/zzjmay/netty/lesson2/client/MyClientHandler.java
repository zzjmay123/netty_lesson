package com.zzjmay.netty.lesson2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Created by zzjmay on 2019/3/14.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String>
{
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

        System.out.println("远程服务地址"+channelHandlerContext.channel().remoteAddress());

        System.out.println("收到的信息msg" + s);

        channelHandlerContext.writeAndFlush("from client:"+ LocalDateTime.now());


    }

    /**
     * 当连接建立后，可以触发发送数据给服务端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("我是来自客户端的消息");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

        ctx.close();
    }
}
