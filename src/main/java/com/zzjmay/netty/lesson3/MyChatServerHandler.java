package com.zzjmay.netty.lesson3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by zzjmay on 2019/3/14.
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //channelGroup

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //获取当前的channel连接
        Channel channel = channelHandlerContext.channel();

        channelGroup.forEach(channel1 -> {
            if(channel != channel1){
                //如果循环到的不是
                channel1.writeAndFlush(channel.remoteAddress() +"发送消息"+s+"\n");
            }else{
                channel1.writeAndFlush("[自己]"+ s);
            }
        });
    }

    /**
     * 连接建立进来
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将连接好的channel，放入channelGroup中
        channelGroup.writeAndFlush("【服务器】"+channel.remoteAddress()+"加入\n");

        channelGroup.add(channel);
    }

    /**
     * 连接断连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("【服务器】"+channel.remoteAddress()+"离开\n");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    /**
     * 链接激活
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        System.out.println(channel.remoteAddress() +"上线");
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        System.out.println(channel.remoteAddress()+"下线");
    }
}
