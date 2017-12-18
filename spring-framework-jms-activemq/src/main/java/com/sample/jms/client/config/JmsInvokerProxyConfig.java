package com.sample.jms.client.config;

import com.sample.jms.service.CheckingAccountService;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;

@Configuration
public class JmsInvokerProxyConfig {

    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    Queue sendMessageQueue;

    /**
     * jms代理对象.
     */
    @Bean
    public JmsInvokerProxyFactoryBean jmsInvokerProxyFactory() {
        //调用代理factory
        JmsInvokerProxyFactoryBean bean = new JmsInvokerProxyFactoryBean();
        //代理的服务
        bean.setServiceInterface(CheckingAccountService.class);
        //jms 连接factory
        bean.setConnectionFactory(connectionFactory);
        //代理调用到的目标Queue
        bean.setQueue(sendMessageQueue);
        return bean;
    }
}
