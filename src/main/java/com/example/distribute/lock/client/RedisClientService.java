package com.example.distribute.lock.client;

import com.example.distribute.lock.config.RedisConfig;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

/**
 * RedisClientService
 *
 * @author hl162981
 * @date 2018/7/7
 */
@Component
public class RedisClientService {

  private static final Logger LOG = LoggerFactory.getLogger(RedisClientService.class);


  @Resource
  private RedisConfig redisConfig;


  private static Pool<Jedis> pool;

  @PostConstruct
  private void init() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(redisConfig.getMaxTotal());
    config.setMaxIdle(redisConfig.getMaxIdle());
    config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
    pool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(),
        redisConfig.getTimeout(), redisConfig.getPassWord());
  }

  private Jedis getResource() {
    if (pool == null) {
      throw new RuntimeException("Redis Client not init!");
    }
    return pool.getResource();
  }

  private void closeResource(Jedis jedis) {
    if (jedis != null) {
      if (jedis.isConnected()) {
        pool.close();
      }
    }
  }


  /**
   * redis调用模板
   * @param rc
   * @param <T>
   * @return
   */
  public <T> T call(RedisCall<T> rc) {
    Jedis jedis = null;
    try {
      jedis = getResource();
      return rc.run(jedis);
    } finally {
      closeResource(jedis);
    }
  }


}
