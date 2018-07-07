package com.example.demo.service;

import com.example.demo.config.YamlConfig;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BizService
 *
 * @author hl162981
 * @date 2018/7/5
 */
@Component
public class BizService {

  @Autowired
  private YamlConfig yamlConfig;

  @PostConstruct
  private void init(){
    System.out.println("业务代码启动");
    System.out.println(yamlConfig.getName());
  }

}
