package com.example.distribute.lock.client;

import redis.clients.jedis.Jedis;

/**
 * RedisCall
 *
 * @author hl162981
 * @date 2018/7/7
 */
public interface RedisCall<T> {


  /**
   * 对redis进行操作
   * @param jedis
   * @return
   */
  T run(Jedis jedis);
}
