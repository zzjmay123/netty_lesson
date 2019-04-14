package com.zzjmay.netty.javaNio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zzjmay on 2019/4/14.
 */
public class NioTest3 {

    public static void main(String[] args) throws Exception {

        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");

        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] context = "Hello java zzjamy".getBytes();
        //将数据写入buffer
        byteBuffer.put(context);

        //先写在读
        byteBuffer.flip();

        //将数据从buffer输出
        fileChannel.write(byteBuffer);


        fileOutputStream.close();







    }
}
