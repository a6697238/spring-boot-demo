package com.example.demo.controller;

import com.example.demo.service.CacheService;
import com.example.demo.service.EsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/7 12:45 下午
 */
@Api(tags = "cache模块")
@Controller
public class CacheController {

    @Autowired
    private CacheService cacheService;


    @RequestMapping(value = "/putCache", method = RequestMethod.GET)
    @ResponseBody
    public String putCache(String key, String val) {
        cacheService.setCache(key, val);
        return "OK";
    }

    @RequestMapping(value = "/getCache", method = RequestMethod.GET)
    @ResponseBody
    public String getCache(String key) {

        return cacheService.getCache(key);
    }

    @RequestMapping(value = "/queryStat", method = RequestMethod.GET)
    @ResponseBody
    public Object putCache() {
        return cacheService.queryStat();
    }


}
