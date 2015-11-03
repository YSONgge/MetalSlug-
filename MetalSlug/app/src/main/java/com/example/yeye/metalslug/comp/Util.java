package com.example.yeye.metalslug.comp;

import java.util.Random;

/**
 * Created by yeye on 2015/11/3.
 */
public class Util {
    public static Random random = new Random();
    //返回一个0~rangede 随机数
    public  static int rand(int range){
        if(range == 0){
            return 0;
        }else {
            return Math.abs(random.nextInt() % range);
        }
    }
}
