package com.reentrant;

import java.util.concurrent.locks.ReentrantLock;
import lombok.SneakyThrows;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/31 7:42 上午
 */
public class LockTest {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    try {
                        reentrantLock.lock();
                        System.out.println(Thread.currentThread().getName() + " ACQUIRE LOCK");
                        Thread.sleep(3000);
                    } finally {
                        reentrantLock.unlock();
                        System.out.println(Thread.currentThread().getName() + " release LOCK");
                    }
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    try {
                        reentrantLock.lock();
                        System.out.println(Thread.currentThread().getName() + " ACQUIRE LOCK");
                        Thread.sleep(3000);
                    } finally {
                        reentrantLock.unlock();
                        System.out.println(Thread.currentThread().getName() + " release LOCK");
                    }
                }
            }
        });
        thread2.start();

    }

}
