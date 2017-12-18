package com.sample.jms.service.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestServer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext("com.sample.jms.config", "com.sample.jms.service.config");
    }
}
