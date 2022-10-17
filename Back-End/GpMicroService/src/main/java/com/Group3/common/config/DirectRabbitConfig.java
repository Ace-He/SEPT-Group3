package com.Group3.common.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@Component
public class DirectRabbitConfig {

    @Bean
    public Queue rabbitmqDemoDirectQueue(){
        /**
         * Param:
         * 1、name:    name of the queue
         * 2、durable: durable or not
         * 3、exclusive: Exclusive or not exclusive. If set to true, it is defined as an exclusive queue. Only the creator can use the queue. So it's private.
         * 4、autoDelete: Whether to automatically delete. This is a temporary queue. When the last consumer is disconnected, it is automatically deleted.
         * */

        return new Queue(RabbitMQConfig.RABBITMQ_DEMO_TOPIC_GP, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange(){
        //Direct exchange
        return new DirectExchange(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, true,false);
    }

    @Bean
    public Binding bindDirect(){
        //Bind the exchange to the queue and set the match key
        return BindingBuilder
                //Binding queue
                .bind(rabbitmqDemoDirectQueue())
                //To Exchange
                .to(rabbitmqDemoDirectExchange())
                //And set the match key
                .with(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING);
    }

}
