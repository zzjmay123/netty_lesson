package com.zzjmay.netty.lesson6.client;

import com.zzjmay.netty.lesson6.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * Created by zzjmay on 2019/3/21.
 */
public class TestPbClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.MyMessage myMessage) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当链接建立以后，发送对应的类型
        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;

        switch (randomInt){
            case 0:
                myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                        .setPerson(MyDataInfo.Person.newBuilder().setAddress("上海")
                                    .setAge(29).setName("网友").build()).build();
                break;
            case 1:
                myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType)
                        .setDog(MyDataInfo.Dog.newBuilder()
                                .setAge(19).setName("Lily").build()).build();
                break;
            default:
                myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType)
                        .setCat(MyDataInfo.Cat.newBuilder()
                                .setCity("江西上饶").setName("zzjmay").build()).build();

        }

        System.out.println("客户端发送的消息 myMessage:"+myMessage.toString());
        //发送消息
        ctx.channel().writeAndFlush(myMessage);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常后关闭链接
        ctx.channel().close();
    }
}
