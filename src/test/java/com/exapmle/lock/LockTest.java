package com.exapmle.lock;

import com.example.distribute.lock.Lock;
import com.example.distribute.lock.LockFactory;
import com.example.distribute.lock.RedisLockApplication;
import com.example.distribute.lock.client.RedisClient;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

/**
 * LockTest
 *
 * @author hl162981
 * @date 2018/7/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisLockApplication.class)
@ActiveProfiles("distribute-lock")
@Slf4j
public class LockTest {

  @Resource
  private LockFactory lockFactory;

  @Test
  public void setTest() {
    RedisClient redisClient = lockFactory.getRedisClient();
    redisClient.setObj("ceshiLock", "TRUE");
    System.out.println(redisClient.getObj("ceshiLock", String.class));
  }

  @Test
  public void lockTest() throws InterruptedException {
    Lock lock = lockFactory.newInstance("testLock");
    for (int i = 0; i < 5; i++) {
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            if (lock.lock()) {
              System.out.println(Thread.currentThread() + "get lock");
            }
          } finally {
            lock.unlock();
          }
        }
      });
      thread.start();
    }
    Thread.sleep(100000);
  }

  @Test
  public void tryLockTest() throws InterruptedException {
    Lock lock = lockFactory.newInstance("testLock", 10);
    for (int i = 0; i < 5; i++) {
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            if (lock.tryLock(3)) {
              System.out.println(Thread.currentThread() + "get lock");
              Thread.sleep(1000);
            }
          } catch (Exception e) {

          } finally {
            lock.unlock();
            System.out.println(Thread.currentThread() + "drop lock");
          }
        }
      });
      thread.start();
    }
    Thread.sleep(100000);
  }

}
