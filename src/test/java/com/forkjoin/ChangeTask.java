package com.forkjoin;

import java.util.concurrent.RecursiveTask;
import lombok.SneakyThrows;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/26 11:39 下午
 */
public class ChangeTask extends RecursiveTask<Integer> {

    @SneakyThrows
    @Override
    protected Integer compute() {
        Thread.sleep(5);
        System.out.println(Thread.currentThread().getName() + "_"+"a");
        return 0;
    }
}
