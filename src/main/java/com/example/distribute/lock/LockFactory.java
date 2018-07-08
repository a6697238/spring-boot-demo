package com.example.distribute.lock;

import com.example.distribute.lock.client.RedisClient;
import com.example.distribute.lock.impl.RedisLock;
import javax.annotation.Resource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Lock
 *
 * @author hl162981
 * @date 2018/7/7
 */
@Component
@Profile("distribute-lock")
public class LockFactory {

  @Resource
  private RedisClient redisClient;


  public Lock newInstance(String key, int expire) {
    return new RedisLock(key, expire, redisClient);
  }

  public Lock newInstance(String key, int expire, long tryInterval) {
    return new RedisLock(key, expire, tryInterval, redisClient);

  }

  public Lock newInstance(String key) {
    return new RedisLock(key, redisClient);
  }

  public RedisClient getRedisClient(){
    return redisClient;
  }


}
