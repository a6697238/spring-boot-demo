package com.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/5 10:58 上午
 */
public class MapTest {


    public static void main(String[] args) {

//        initMap();
        treeMap();
    }

    public static void initMap(){
        Map<String,Map<String,String>> queueTaskMap = Maps.newHashMap();
        Map<String, String> adTypeMap = queueTaskMap
                .computeIfAbsent("a", k -> Maps.newHashMap());
        adTypeMap.put("a","a");
        System.out.println(JSON.toJSONString(queueTaskMap));
    }

    public static void treeMap(){
        Map<String, Integer> map = new TreeMap<>();

        map.put("d", 4);
        map.put("b", 2);
        map.put("a", 1);
        map.put("c", 3);

        //这里将map.entrySet()转换成list
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());

        //升序排序
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) * -1);
        for(Map.Entry<String,Integer> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
    }
}
