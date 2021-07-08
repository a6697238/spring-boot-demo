package com.delay.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import lombok.Data;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/7/8 1:55 下午
 */
@Data
public class DelayMsg implements Delayed {

    private String body; // 消息内容
    private long executeTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。

    public DelayMsg(String body, long executeTime) {
        this.body = body;
        this.executeTime = executeTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return executeTime-System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed delayed) {
        return Long.compare(this.executeTime,((DelayMsg) delayed).getExecuteTime());
    }
}
