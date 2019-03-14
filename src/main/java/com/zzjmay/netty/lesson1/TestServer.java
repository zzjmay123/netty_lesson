package com.zzjmay.netty.lesson1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *  netty程序的三步走：
 * 1.编写server
 * 2.添加自己的Initializer
 * 3.在自己的Initializer的管道中添加 handler
 * Created by zzjmay on 2019/3/3.
 */
public class TestServer {

    public static void main(String[] args) {
        //1. 启动线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8877).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
