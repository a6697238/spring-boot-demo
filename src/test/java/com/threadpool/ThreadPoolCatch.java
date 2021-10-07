package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/2 12:13 下午
 */
public class ThreadPoolCatch {

    private static ExecutorService executorService = Executors.newFixedThreadPool(40);

    public static void main(String[] args) {
//        Future future = executorService.submit(() -> doService());
        try {
            executorService.submit(ThreadPoolCatch::doService);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void doService() {
        throw new RuntimeException();
    }
}
