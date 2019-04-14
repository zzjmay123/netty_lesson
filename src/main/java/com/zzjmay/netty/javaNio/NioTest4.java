package com.zzjmay.netty.javaNio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO 方式实现文件的拷贝
 * Created by zzjmay on 2019/4/14.
 */
public class NioTest4 {


    public static void main(String[] args) throws Exception {

        FileInputStream inputStream = new FileInputStream("input.txt");

        FileOutputStream outputStream = new FileOutputStream("output.txt");

        //创建channel对象

        FileChannel inputChannel = inputStream.getChannel();

        FileChannel outChannel = outputStream.getChannel();

        //buffer 缓冲数据
        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true){
            //相当于合理的利用内存空间,复用buffer
            buffer.clear();

            int read = inputChannel.read(buffer);

            System.out.println("read:"+read);

            if(-1 == read){
                break;
            }

            buffer.flip();

            //将数据实时写入输出channel
            outChannel.write(buffer);
        }

        inputChannel.close();
        outChannel.close();

    }
}
