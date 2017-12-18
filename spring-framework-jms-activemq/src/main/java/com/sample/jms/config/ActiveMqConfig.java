package com.sample.jms.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMqConfig {
    //外国佬的习惯 注释第一句话后面要有一个点
    /**
     * 配置 连接工厂.
     *
     * @return ConnectionFactory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
        return connectionFactory;
    }

    /**
     *  发送消息 通过指定的队列.
     * @return ActiveMQQueue
     */
    @Bean
    public Queue sendMessageQueue() {
        ActiveMQQueue activeMqQueue = new ActiveMQQueue("send_message_queue");
        return activeMqQueue;
    }
}
