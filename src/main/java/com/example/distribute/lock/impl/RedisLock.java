package com.example.distribute.lock.impl;

import com.example.distribute.lock.Lock;
import com.example.distribute.lock.client.RedisCall;
import com.example.distribute.lock.client.RedisClient;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * RedisLock
 *
 * @author hl162981
 * @date 2018/7/7
 */
public class RedisLock implements Lock {

  private static final Logger LOG = LoggerFactory.getLogger(RedisLock.class);

  public static final int DEFAULT_EXPIRE_TIME = 10000;
  public static final long DEFAULT_TRY_INTERVAL = 50;
  public static final String SET_SUCCESS = "OK";
  private int expire;
  private String key;
  private String val;
  private long tryInterval;


  private RedisClient redisClient;


  public RedisLock(String key, RedisClient redisClient) {
    this.key = "distribute::lock::" + key;
    this.val = UUID.randomUUID().toString();
    this.expire = DEFAULT_EXPIRE_TIME;
    this.tryInterval = DEFAULT_TRY_INTERVAL;
    this.redisClient = redisClient;
  }

  public RedisLock(String key, int expire, RedisClient redisClient) {
    this.key = "distribute::lock::" + key;
    this.val = UUID.randomUUID().toString();
    this.redisClient = redisClient;
    if (expire > 0) {
      this.expire = expire * 1000;
    } else {
      this.expire = DEFAULT_EXPIRE_TIME;
    }
  }

  public RedisLock(String key, int expire, long tryInterval,
      RedisClient redisClientService) {
    this.key = "distribute::lock::" + key;
    this.val = UUID.randomUUID().toString();
    this.redisClient = redisClient;
    if (expire > 0) {
      this.expire = expire * 1000;
    } else {
      this.expire = DEFAULT_EXPIRE_TIME;
    }
    if (tryInterval > 0) {
      this.tryInterval = DEFAULT_TRY_INTERVAL;
    } else {
      this.tryInterval = DEFAULT_TRY_INTERVAL;
    }
  }


  @Override
  public boolean lock() {
    return redisClient.call(new RedisCall<Boolean>() {
      @Override
      public Boolean run(Jedis jedis) {
        if (setNx(key, val, expire)) {
          LOG.debug("get lock success ,key=" + key
              + ", expire seconds=" + expire);
          return true;
        } else {
          LOG.debug("get lock fail, key=" + key);
          return false;
        }
      }
    });
  }

  @Override
  public boolean tryLock(int timeout) {
    long tryTime = System.currentTimeMillis() + timeout * 1000L;
    while (System.currentTimeMillis() < tryTime) {
      if (setNx(key, val, expire)) {
        LOG.debug("get lock success ,key=" + key
            + ", expire seconds=" + expire);
        return true;
      } else {
        LOG.debug("get lock fail, key=" + key);
      }
      try {
        TimeUnit.MILLISECONDS.sleep(tryInterval);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    return false;
  }

  @Override
  public void unlock() {
    redisClient.call(new RedisCall<Boolean>() {
      @Override
      public Boolean run(Jedis jedis) {
        jedis.eval(
            "if redis.call('get',KEYS[1]) == ARGV[1] then \n return redis.call('del',KEYS[1]) \n else return 0 \n end",
            Arrays.asList(key), Arrays.asList(val));
        return true;
      }
    });
  }


  /**
   * setNx 且设置超时时间
   */
  private boolean setNx(final String key, final String val,
      final int expireTime) {
    return redisClient.call(new RedisCall<Boolean>() {
      @Override
      public Boolean run(Jedis jedis) {
        String response = jedis.set(key, val, "NX", "PX",
            expireTime);
        return SET_SUCCESS.equals(response);
      }
    });

  }
}
