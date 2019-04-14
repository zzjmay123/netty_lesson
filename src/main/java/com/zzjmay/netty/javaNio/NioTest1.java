package com.zzjmay.netty.javaNio;

import java.nio.IntBuffer;
import java.util.Random;

/**
 * Created by zzjmay on 2019/4/14.
 */
public class NioTest1 {


    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);

        for(int i=0;i<intBuffer.capacity();i++){
            int randomNumber = new Random().nextInt(20);
            intBuffer.put(randomNumber);
        }
        //状态翻转
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
