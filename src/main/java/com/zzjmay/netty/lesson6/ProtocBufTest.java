package com.zzjmay.netty.lesson6;

import com.google.protobuf.InvalidProtocolBufferException;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.xml.crypto.Data;

/**
 * Created by zzjmay on 2019/3/20.
 */
public class ProtocBufTest {

    public static void main(String[] args) throws Exception {
        //构造对象
      DataInfo.Student student =  DataInfo.Student.newBuilder()
                .setAddress("北京京东")
                .setAge(19)
                .setName("zzjmay").build();

      //将对象转化成字节数组进行传输，序列化操作
        byte[] student2ByteArray = student.toByteArray();

        //反序列化操作
        DataInfo.Student student1 = DataInfo.Student.parseFrom(student2ByteArray);


        System.out.println(student1.getAddress());
        System.out.println(student1.getAge());
        System.out.println(student1.getName());
    }
}
