package com.zzjmay.netty.javaNio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zzjmay on 2019/4/14.
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");

        FileChannel fileChannel = fileInputStream.getChannel();

        //每一个channel操作的都是buffer中数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //读取数据一定是在buffer中进行的
        fileChannel.read(byteBuffer);

        //先读在写
        byteBuffer.flip();

        while (byteBuffer.remaining() > 0){
            byte b = byteBuffer.get();
            System.out.println("Character:"+(char)b);
        }

        fileInputStream.close();
    }
}
