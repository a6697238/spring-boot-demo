package com.example.demo.controller;

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
@Api(tags = "es模块")
@Controller
public class EsController {

    @Autowired
    private EsService esService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/esIndexTest", method = RequestMethod.GET)
    @ResponseBody
    public String esIndexTest() {
        esService.testIndex();
        return "OK";
    }



}
