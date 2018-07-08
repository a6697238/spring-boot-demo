package com.example.distribute.lock.client;

import com.alibaba.fastjson.JSON;
import com.example.distribute.lock.config.RedisConfig;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
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
public class RedisClient {

  private static final Logger LOG = LoggerFactory.getLogger(RedisClient.class);


  public static final String SET_SUCCESS = "OK";


  @Resource
  private RedisConfig redisConfig;


  private Pool<Jedis> pool;

  @PostConstruct
  private void init() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(redisConfig.getMaxTotal());
    config.setMaxIdle(redisConfig.getMaxIdle());
    config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
    if (StringUtils.isNotEmpty(redisConfig.getPassWord())) {
      pool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(),
          redisConfig.getTimeout(), redisConfig.getPassWord());
    } else {
      pool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(),
          redisConfig.getTimeout());
    }
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
        jedis.close();
      }
    }
  }

  public void setObj(String key, Object o) {
    String val = JSON.toJSONString(o);
    call(new RedisCall<Boolean>() {
      @Override
      public Boolean run(Jedis jedis) {
        String returnMsg = jedis.set(key, val);
        return true;
      }
    });
  }

  public void setObj(String key, Object o, int expire) {
    String val = JSON.toJSONString(o);
    call(new RedisCall<Boolean>() {
      @Override
      public Boolean run(Jedis jedis) {
        jedis.set(key, val);
        jedis.expire(key, expire);
        return true;
      }
    });
  }

  public void increasement(String key) {
    call(new RedisCall<Boolean>() {
      @Override
      public Boolean run(Jedis jedis) {
        jedis.incr(key);
        return true;
      }
    });
  }

  public <T> T getObj(String key, Class<T> clazz) {
    String val = call(new RedisCall<String>() {
      @Override
      public String run(Jedis jedis) {
        return jedis.get(key);
      }
    });
    if (StringUtils.isNotEmpty(val)) {
      return JSON.parseObject(val, clazz);
    } else {
      return null;
    }
  }


  /**
   * redis调用模板
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
