package com.watch;

import org.apache.commons.lang3.time.StopWatch;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/26 7:56 下午
 */
public class StopWatchTest {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(3000);
        stopWatch.stop();
        System.out.println(stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();
        Thread.sleep(3000);
        stopWatch.stop();
        System.out.println(stopWatch.getTime());

    }

}
