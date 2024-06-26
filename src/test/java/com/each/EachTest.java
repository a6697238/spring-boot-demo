package com.each;

import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.util.Lists;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/11/1 7:16 下午
 */
public class EachTest {

    public static void main(String[] args) {

        Map<String,String> has = Maps.newHashMap();
        Map<String,String> link = new LinkedHashMap<>();

        has.put("1","1");
        has.put("5","5");
        has.put("4","4");
        has.put("2","2");

        link.put("1","1");
        link.put("5","5");
        link.put("4","4");
        link.put("2","2");

        for(String key : has.keySet()){
            System.out.println("has key : " + key);
        }

        for(String key : link.keySet()){
            System.out.println("link key : " + key);
        }

    }

}
