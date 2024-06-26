package com.delay.queue;

import java.util.PriorityQueue;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/5 11:15 上午
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
//        removeQueue();
//        testComparator();
//        sortQueue();
        sortStudentQueue();
    }


    private static void testComparator(){
        //通过改造器指定排序规则
        StudentCompareQueue q = new StudentCompareQueue();

        StudentCompare a = new StudentCompare("will", 17);
        StudentCompare b = new StudentCompare("dafei", 20);
        StudentCompare c = new StudentCompare("setf", 30);
        StudentCompare d = new StudentCompare("bunny", 20);
        StudentCompare e = new StudentCompare("bunny", 20);

        //入列
        q.add(a);
        q.add(b);
        q.add(c);
        q.add(d);



        //出列
        System.out.println(q.poll());  //Student{name='will', score=17}
        System.out.println(q.poll());  //Student{name='bunny', score=20}
        System.out.println(q.poll());  //Student{name='dafei', score=20}
        System.out.println(q.poll());  //Student{name='setf', score=30}

    }

    private static void removeQueue(){
        //通过改造器指定排序规则
        PriorityQueue<Student> q = new PriorityQueue<>((o1, o2) -> {
            //按照分数低到高，分数相等按名字
            if (o1.getScore() == o2.getScore()) {
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getScore() - o2.getScore();
        });
        Student a = new Student("will", 17);
        Student b = new Student("dafei", 20);
        Student c = new Student("setf", 30);
        Student d = new Student("bunny", 20);
        Student e = new Student("bunny", 20);

        //入列
        q.add(a);
        q.add(b);
        q.add(c);
        q.add(d);

        System.out.println(e.equals(d));
        System.out.println("remove res is :" +q.remove(d));
        System.out.println("remove res is :" +q.remove(e));


        //出列
        System.out.println(q.poll());  //Student{name='will', score=17}
        System.out.println(q.poll());  //Student{name='bunny', score=20}
        System.out.println(q.poll());  //Student{name='dafei', score=20}
        System.out.println(q.poll());  //Student{name='setf', score=30}

    }

    private static void sortQueue(){
        //通过改造器指定排序规则
        PriorityQueue<Student> q = new PriorityQueue<>((o1, o2) -> {
            //按照分数低到高，分数相等按名字
            if (o1.getScore() == o2.getScore()) {
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getScore() - o2.getScore();
        });
        //入列
        q.offer(new Student("dafei", 20));
        q.offer(new Student("will", 17));
        q.offer(new Student("setf", 30));
        q.offer(new Student("bunny", 20));

        //出列
        System.out.println(q.poll());  //Student{name='will', score=17}
        System.out.println(q.poll());  //Student{name='bunny', score=20}
        System.out.println(q.poll());  //Student{name='dafei', score=20}
        System.out.println(q.poll());  //Student{name='setf', score=30}
    }

    private static void sortStudentQueue(){
        //通过改造器指定排序规则
        StudentQueue q = new StudentQueue(null);
        //入列
        q.offer(new Student("dafei", 20));
        q.offer(new Student("will", 17));
        q.offer(new Student("setf", 30));
        q.offer(new Student("bunny", 20));

        //出列
        System.out.println(q.poll());  //Student{name='will', score=17}
        System.out.println(q.poll());  //Student{name='bunny', score=20}
        System.out.println(q.poll());  //Student{name='dafei', score=20}
        System.out.println(q.poll());  //Student{name='setf', score=30}
    }
}
