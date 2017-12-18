package com.sample.jms.client.main;

import com.sample.jms.service.CheckingAccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestClient {

    /**
     * 测试方法.
     * @param args 传参
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext("com.sample.jms.config", "com.sample.jms.client.config");
        CheckingAccountService checkingAccountService = (CheckingAccountService) context.getBean("jmsInvokerProxyFactory");
        checkingAccountService.cancelAccount(10L);
    }
}
