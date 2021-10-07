package com.guavapackage;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/6 4:08 下午
 */
public class TableTest {

    public static void main(String[] args) {
        Table<Integer, String, String> queueTaskTable = HashBasedTable.create();

        queueTaskTable.put(1, "a", "a");
        System.out.println(queueTaskTable.get(1,"a"));
        System.out.println(JSON.toJSONString(queueTaskTable));
    }

}
