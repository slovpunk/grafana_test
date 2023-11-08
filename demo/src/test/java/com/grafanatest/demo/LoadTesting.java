package com.grafanatest.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;
import static us.abstracta.jmeter.javadsl.JmeterDsl.influxDbListener;
import static us.abstracta.jmeter.javadsl.JmeterDsl.testPlan;
import static us.abstracta.jmeter.javadsl.JmeterDsl.threadGroup;
import com.grafanatest.demo.application.UserService;
import java.io.IOException;
import java.time.Duration;
import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

//@ContextConfiguration(classes = { UserService.class })
@SpringBootTest
public class LoadTesting {

  @Autowired
  UserService userService;
  private static final Integer THREADS = 15;
  private static final Integer ITERATIONS = 1000;

  @Test
  void test() throws IOException {

    String user = userService.getJsonUser();

    TestPlanStats stats = testPlan(
        threadGroup(THREADS, ITERATIONS,
            httpSampler("http://localhost:8080/user")
                .method(HTTPConstants.POST)
                .post(s -> user, ContentType.APPLICATION_JSON)
            , influxDbListener("http://localhost:8086/api/v2/write?db=mydb&org=myOrg&bucket=myBucket")
                .token("mySecretToken")
        )).run();

    assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));

  }
}
