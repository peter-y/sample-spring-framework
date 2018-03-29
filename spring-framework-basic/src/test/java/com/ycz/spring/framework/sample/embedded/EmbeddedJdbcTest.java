package com.ycz.spring.framework.sample.embedded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = "classpath:spring/spring-embedded-tx.xml")
public class EmbeddedJdbcTest extends AbstractTestNGSpringContextTests {

    private Logger logger = LoggerFactory.getLogger(EmbeddedJdbcTest.class);

    public void testLogger() {
        logger.info("test");
    }

    //操作跟操作普通数据库一致
}
