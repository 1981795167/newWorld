package com.asia.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author Xavier.liu
 * @date 2020/5/18 14:33
 */
@Slf4j
@Configuration
public class RabbitMqConfig {

    /**
     *  rabbitListenerContainerFactory 方法名不能更改！！！ 要不然acknowledge.manual不能生效
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        int currency = Runtime.getRuntime().availableProcessors();
        container.setConcurrentConsumers(currency);
        container.setMaxConcurrentConsumers(currency * 2);
        container.setPrefetchCount(10);
        return container;
    }


    @Bean
    public DirectExchange directExchange(){return new DirectExchange("asia.liu.exchange");}

    @Bean
    public Queue queue(){return new Queue("asia.liu.queue");}

    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("rk");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        /**
         *  publisher-confirm
         *  确认是否到达broker exchange
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause)->{
            if (!ack) {
                Assert.notNull(correlationData,"correlationData not null");
                log.info("fail to send message to exchange,id:{},{}",correlationData.getId(),cause);
            }
        });
        return new RabbitTemplate(connectionFactory);
    }
}
