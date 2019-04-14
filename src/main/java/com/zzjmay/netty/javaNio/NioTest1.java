package com.zzjmay.netty.javaNio;

import java.nio.IntBuffer;
import java.util.Random;

/**
 * Created by zzjmay on 2019/4/14.
 */
public class NioTest1 {


    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);

        for(int i=0;i<5;i++){
            int randomNumber = new Random().nextInt(20);
            intBuffer.put(randomNumber);
        }
        System.out.println("before flip limit:"+intBuffer.limit());
        //状态翻转
        intBuffer.flip();

        System.out.println("after flip limit:"+intBuffer.limit());

        while (intBuffer.hasRemaining()){
            System.out.println("postition:"+intBuffer.position());
            System.out.println("limit:"+intBuffer.limit());
            System.out.println("capacity:"+intBuffer.capacity());
            System.out.println(intBuffer.get());
        }
    }
}
