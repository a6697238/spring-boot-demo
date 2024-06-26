package com.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.assertj.core.util.Lists;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/26 11:53 下午
 */
public class FutureTest {

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<String>> futureList = Lists.newArrayList();
        for (int i=0;i<100;i++) {
            int finalI = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("start to load"+ finalI);
                    Thread.sleep(3000);
                    System.out.println("end to load"+ finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return String.valueOf(finalI);
            }, executor);
            futureList.add(future);
        }

        for(CompletableFuture<String> res:futureList){
            System.out.println("RES IS " + res.get());
        }


    }

}
