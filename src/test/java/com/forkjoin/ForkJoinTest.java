package com.forkjoin;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/26 11:10 下午
 */
public class ForkJoinTest {

    public static void main(String[] args) throws Exception {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //显然使用IntStream.parallel().sum()可以方便得到结果
        // 且parallel也是使用的ForkJoinPool，这是后话，我们本例就是测试ForkJoinTask的分解
//        int[] numbers = IntStream.rangeClosed(0, 1_000_000).toArray();
//        long begin = System.currentTimeMillis();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new ChangeTask());
        System.out.println("累加结果为：" + submit.get());
//        System.out.println("运算耗时：" + (System.currentTimeMillis() - begin));
    }

}
