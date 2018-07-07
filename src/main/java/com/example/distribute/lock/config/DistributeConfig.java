package com.example.distribute.lock.config;

import javax.annotation.Resource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * YamlConfig
 *
 * @author hl162981
 * @date 2018/7/5
 */

@Configuration
@ComponentScan(value = "com.example.distribute.lock")
@Profile("distribute-lock")
public class DistributeConfig {



}
