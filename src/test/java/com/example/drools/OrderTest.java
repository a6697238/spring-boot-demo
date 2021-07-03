package com.example.drools;

import com.example.demo.DemoApplication;
import com.example.demo.entity.OrderEntity;
import com.example.distribute.lock.Lock;
import com.example.distribute.lock.LockFactory;
import com.example.distribute.lock.RedisLockApplication;
import com.example.distribute.lock.client.RedisClient;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * LockTest
 *
 * @author hl162981
 * @date 2018/7/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class OrderTest {

  @Resource
  private KieContainer kieContainer;

  @Test
  public void setTest() {
    KieSession session = kieContainer.newKieSession();
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setOriginalPrice(150);

    session.insert(orderEntity);
    session.fireAllRules();

    log.info("orderEntity res is : {}",orderEntity);
    session.dispose();
  }

}
