package com.random;

import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.Random;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/11/8 3:17 下午
 */
public class RandomDemo {

    public static void main(String[] args) {

        Random r = new Random();
        System.out.println(r.nextInt(10));
        System.out.println(r.nextInt(10));
        System.out.println(r.nextInt(10));
        System.out.println(r.nextInt(10));
        System.out.println(r.nextInt(10));

        String val = "[\n"
                + "  \"winghou\"\n"
                + "]";
        List<String> whiteList = JSON.parseArray(val, String.class);
        System.out.println(whiteList);

    }

}
