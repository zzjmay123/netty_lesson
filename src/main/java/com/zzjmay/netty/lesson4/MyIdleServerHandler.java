package com.zzjmay.netty.lesson4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by zzjmay on 2019/3/16.
 */
public class MyIdleServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 用户事件触发器
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if(evt instanceof IdleStateEvent){
            //如果触发事件是空闲事件
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

            String eventType = null;

            switch (idleStateEvent.state()){
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + "超时事件："+eventType);


        }
    }
}
