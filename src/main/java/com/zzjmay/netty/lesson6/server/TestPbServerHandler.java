package com.zzjmay.netty.lesson6.server;

import com.zzjmay.netty.lesson6.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 自定义反序列化
 * Created by zzjmay on 2019/3/21.
 */
public class TestPbServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.MyMessage myMessage) throws Exception {

        MyDataInfo.MyMessage.DataType dataType =myMessage.getDataType();

        switch (dataType){
            case PersonType:
                MyDataInfo.Person person = myMessage.getPerson();
                System.out.println(person.getAddress());
                System.out.println(person.getAge());
                System.out.println(person.getName());
                break;

            case CatType:
                MyDataInfo.Cat cat = myMessage.getCat();
                System.out.println(cat.getCity());
                System.out.println(cat.getName());
                break;

            case DogType:
                MyDataInfo.Dog dog = myMessage.getDog();
                System.out.println(dog.getAge());
                System.out.println(dog.getName());
                break;
        }

    }
}
