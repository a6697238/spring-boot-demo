package com.example.distribute.lock.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * YamlConfig
 *
 * @author hl162981
 * @date 2018/7/5
 */

@Setter
@Getter
@Component
@ConfigurationProperties("distribute.lock.redis")
public class RedisConfig {

  private String host;

  private Integer port;

  private String passWord;

  private Integer maxTotal;

  private Integer maxIdle;

  private Integer maxWaitMillis;

  private Integer timeout = 2000;
}
