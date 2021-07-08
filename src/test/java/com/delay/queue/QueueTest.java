package com.delay.queue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.logging.SimpleFormatter;
import lombok.SneakyThrows;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/7/8 2:09 下午
 */
public class QueueTest {

    @SneakyThrows
    public static void main(String[] args) {
        DelayMsg delayMsg1 = new DelayMsg("1",System.currentTimeMillis()+5000);
        DelayMsg delayMsg2 = new DelayMsg("3",System.currentTimeMillis()+15000);
        DelayMsg delayMsg3 = new DelayMsg("2",System.currentTimeMillis()+10000);
        DelayMsg delayMsg4 = new DelayMsg("4",System.currentTimeMillis()+20000);

        DelayQueue<DelayMsg> queue = new DelayQueue<>();
        queue.add(delayMsg1);
        queue.add(delayMsg2);
        queue.add(delayMsg3);
        queue.add(delayMsg4);

        while (queue.size()>0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println("time " +sdf.format(new Date()) + " body " +  queue.take().getBody());
        }
    }

}
