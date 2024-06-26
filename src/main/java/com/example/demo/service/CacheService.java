package com.example.demo.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/22 10:27 下午
 */
@Service
@Slf4j
public class CacheService {

    private LoadingCache<String, String> testCache = CacheBuilder.newBuilder()
            .initialCapacity(5)
            .maximumSize(5)
            .recordStats()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    log.info("load from other");
                    return "1";
                }
            });

    public void setCache(String key, String val) {
        testCache.put(key, val);
    }

    @SneakyThrows
    public String getCache(String key) {
        return testCache.get(key);
    }

    public String queryStat() {
        return testCache.stats().toString();
    }
}
