package com.map;

import com.alibaba.fastjson.JSON;
import com.delay.queue.Student;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
//        treeMap();
//        sortTest();
        testObject();
    }

    public static void testObject(){
        Student student = new Student();
        Map<String,Student> map = new HashMap<>();
        student.setName("aaa");
        map.put("aa",student);

        Student student1 = map.get("aa");
        student1.setName("bb");

        Student student2 = map.get("aa");
        System.out.println(student2);

    }

    public static void initMap() {
        Map<String, Map<String, String>> queueTaskMap = Maps.newHashMap();
        Map<String, String> adTypeMap = queueTaskMap
                .computeIfAbsent("a", k -> Maps.newHashMap());
        adTypeMap.put("a", "a");
        System.out.println(JSON.toJSONString(queueTaskMap));
    }

    public static void sortTest() {
        String mapStr = "{\"2016,MP-教育\":6373,\"3001,通投-游戏\":4,\"1008,视听阅读\":0,\"2013,MP-在线阅读（废弃）\":0,\"3006,通投-教育金融\":579,\"1004,金融&教育\":1103,\"2014,自媒体资讯\":347,\"2011,MP-医疗服务(废弃)\":0,\"3005,通投-电商\":0,\"2009,MP-网服\":20,\"2006,MP-汽车（废弃）\":0,\"1007,合约广告\":3559,\"2001,MP-电商\":20,\"2019,MP-金融\":0,\"2008,MP-摄影（废弃）\":0,\"2003,MP-服务（废弃）\":0,\"3002,通投-其他\":0,\"1010,直购电商+快消\":2529,\"2015,MP-医疗健康(废弃)\":0,\"2007,MP-商业（废弃）\":0,\"1003,游戏\":105,\"2018,MP-视听阅读\":0,\"2010,MP-医疗电商(废弃)\":0,\"1002,医疗\":0,\"2005,MP-金融（废弃）\":0,\"3004,通投-网服\":114,\"1001,网服\":0,\"3003,通投-视听阅读\":0,\"2002,MP-房产(废弃)\":0,\"1006,综合\":1710,\"2004,MP-教育培训（废弃）\":0,\"1009,平台电商\":24,\"2012,MP-游戏\":202,\"2017,MP-综合(废弃)\":0,\"1005,电商（废弃）\":0}";
        Map<String,Integer> channelCountMap = JSON.parseObject(mapStr,Map.class);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(channelCountMap.entrySet());
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) * -1);
        Set<String> channelSet = Sets.newTreeSet();
        for (int i = 0; i < 20; i++) {
            channelSet.add(list.get(i).getKey().split(",")[0]);
        }
        System.out.println(JSON.toJSONString(channelSet));
    }

    public static void treeMap() {
        Map<String, Integer> map = new TreeMap<>();

        map.put("d", 4);
        map.put("b", 2);
        map.put("a", 1);
        map.put("c", 3);

        //这里将map.entrySet()转换成list
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        //升序排序
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) * -1);
        for (Map.Entry<String, Integer> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }
}
