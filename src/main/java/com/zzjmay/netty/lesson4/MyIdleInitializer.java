package com.zzjmay.netty.lesson4;

import com.zzjmay.netty.lesson2.server.MyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


/**
 * Created by zzjmay on 2019/3/16.
 */
public class MyIdleInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //新建管道
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        //新增空闲超时处理器，用于心跳检测机制
        //readerIdleTime 读空闲  客户端没有发送消息给服务器端的时间
        //writerIdleTime 写空闲  服务器端没有发送消息给客户端的时间
        //allIdleTime 读写空闲
        channelPipeline.addLast(new IdleStateHandler(5,7,10, TimeUnit.SECONDS));

        //添加自己的超时处理器
        channelPipeline.addLast(new MyIdleServerHandler());

    }
}
