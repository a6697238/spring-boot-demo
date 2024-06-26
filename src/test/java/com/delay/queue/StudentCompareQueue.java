package com.delay.queue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/31 9:48 下午
 */
public class StudentCompareQueue extends PriorityBlockingQueue<StudentCompare> {

    @Override
    public Comparator<? super StudentCompare> comparator() {
        return new Comparator<StudentCompare>() {
            @Override
            public int compare(StudentCompare o1, StudentCompare o2) {
                System.out.println("StudentCompareQueue compare run");
                return o1.getScore() > o2.getScore() ? 1 : 0;
            }
        };
    }
}
