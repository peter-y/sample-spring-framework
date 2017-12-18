package com.sample.jms.service.config;

import com.sample.jms.service.CheckingAccountService;
import com.sample.jms.service.SimpleCheckingAccountService;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;

@Configuration
@Configurable
public class JmsInvokerServiceConfig {

    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    Queue sendMessageQueue;

    @Bean
    public CheckingAccountService checkingAccountService() {
        return new SimpleCheckingAccountService();
    }

    /**
     * 一个jms服务调用者.
     */
    @Bean
    public JmsInvokerServiceExporter jmsInvokerServiceExporter() {
        //服务调用出口
        JmsInvokerServiceExporter jmsinvoker = new JmsInvokerServiceExporter();
        //实际的处理类
        jmsinvoker.setService(checkingAccountService());
        jmsinvoker.setServiceInterface(CheckingAccountService.class);
        return jmsinvoker;
    }

    /**
     * message 监听 容器.
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        //消息监听容器
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        //设置连接池
        listenerContainer.setConnectionFactory(connectionFactory);
        //设置目标地 需要监听的队列
        listenerContainer.setDestination(sendMessageQueue);
        //并发消费者数量
        listenerContainer.setConcurrentConsumers(3);
        //消息监听 处理，可运行时替换
        listenerContainer.setMessageListener(jmsInvokerServiceExporter());
        return listenerContainer;
    }
}
