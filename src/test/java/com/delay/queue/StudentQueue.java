package com.delay.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/11/3 4:19 下午
 */
public class StudentQueue extends PriorityQueue {

    private final Comparator<Student> comparator;

    public StudentQueue(Comparator<Student> comparator) {
        this.comparator = (o1, o2) -> {
            if (o1.getScore() == o2.getScore()) {
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getScore() - o2.getScore();
        };
    }


}
