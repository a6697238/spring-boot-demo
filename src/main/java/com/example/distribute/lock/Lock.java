package com.example.distribute.lock;

/**
 * Lock
 *
 * @author hl162981
 * @date 2018/7/7
 */
public interface Lock {

  /**
   * 获取锁，一次性无等待
   */
  boolean lock();

  /**
   * 尝试获取锁，可以设置等待时间
   */
  boolean tryLock(int timeout);

  /**
   * 释放锁
   */
  void unlock();


}
