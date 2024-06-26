package com.settest;

import com.delay.queue.Student;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/11/5 10:37 上午
 */
public class HashSet {

    public static void main(String[] args) {
        Map<Student,Boolean> certificationCosts = new ConcurrentHashMap<>();
        Set<Student> tidSet = Collections.newSetFromMap(certificationCosts);

        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();

        tidSet.add(student1);
        tidSet.add(student2);
        tidSet.add(student3);

        tidSet.remove(student1);
        System.out.println(tidSet);
    }

}
