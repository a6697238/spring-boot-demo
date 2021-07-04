package com.example.drools;

import com.example.demo.DemoApplication;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.TruthEntity;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.springframework.boot.test.context.SpringBootTest;
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
public class TruthTest {

  @Resource
  private KieContainer kieContainer;

  @Test
  public void setTest() {
    KieSession session = kieContainer.newKieSession();
    TruthEntity truthEntity1 = new TruthEntity();
    truthEntity1.setTruth001(true);
    truthEntity1.setTruth002(true);


    TruthEntity truthEntity2 = new TruthEntity();
    truthEntity2.setTruth001(true);
    truthEntity2.setTruth002(false);

    session.insert(truthEntity1);
    session.insert(truthEntity2);
    session.fireAllRules(new RuleNameStartsWithAgendaFilter("truth_rule_"));


    log.info("truthEntity res is : {}",truthEntity1);
    log.info("truthEntity res is : {}",truthEntity2);

    session.dispose();

    

  }

}
