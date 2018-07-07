//package com.example.distribute.lock.impl;
//
//import com.example.distribute.lock.Lock;
//import com.example.distribute.lock.client.RedisCall;
//import com.example.distribute.lock.client.RedisClientService;
//import com.example.distribute.lock.config.RedisConfig;
//import javax.annotation.Resource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//
///**
// * RedisLock
// *
// * @author hl162981
// * @date 2018/7/7
// */
//@Component
//public class RedisLock implements Lock {
//
//  private static final Logger LOG = LoggerFactory.getLogger(RedisLock.class);
//
//  private
//
//  @Resource
//  private RedisClientService redisClientService;
//
//
//  @Override
//  public boolean lock() {
//    return redisClientService.call(new RedisCall<Boolean>() {
//      @Override
//      public Boolean run(Jedis jedis) {
//        if (setNx(key, val, expire)) {
//          if (LOG.isDebugEnabled()) {
//            LOG.debug("get lock success ,key=" + key
//                + ", expire seconds=" + expire);
//          }
//          return true;
//        } else {
//          LOG.debug("get lock fail, key=" + key);
//          return false;
//        }
//      }
//    });
//  }
//
//  @Override
//  public boolean tryLock(int timeout) {
//    return false;
//  }
//
//  @Override
//  public void unlock() {
//
//  }
//
//
//  /**
//   * setNx 且设置超时时间
//   *
//   * @param key
//   * @param val
//   * @param expireTime
//   * @return
//   */
//  private boolean setNx(final String key, final String val,
//      final int expireTime) {
//    return redisClientService.call(new RedisCall<Boolean>() {
//      @Override
//      public Boolean run(Jedis jedis) {
//        if (RedisClientUtils.isNewVersion()) {
//          String response = jedis.set(key, val, "NX", "PX",
//              expireTime);
//          return SET_SUCCESS.equals(response);
//        } else {
//          if (jedis.setnx(key, val) == 1) {
//            jedis.expire(key, expireTime / 1000);
//            return true;
//          }
//          return false;
//        }
//      }
//    });
//
//  }
//}
