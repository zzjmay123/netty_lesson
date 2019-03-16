package com.zzjmay.netty.lesson5;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by zzjmay on 2019/3/16.
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline channelPipeline = socketChannel.pipeline();

        channelPipeline.addLast(new HttpServerCodec());
        //按块处理
        channelPipeline.addLast(new ChunkedWriteHandler());
        //用于将多个块的Http数据整合为FullHttpRequest
        channelPipeline.addLast(new HttpObjectAggregator(8192));
        //webSocket的协议的格式
        //ws://server:port/context_path
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/zzjmay"));
        //添加自己的处理器
        channelPipeline.addLast(new TextWebSocketFrameHandler());

    }
}
