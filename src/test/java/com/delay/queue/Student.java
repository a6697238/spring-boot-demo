package com.delay.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/5 11:15 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;  //名字
    private int score;    //分数
    //省略getter/setter
}
