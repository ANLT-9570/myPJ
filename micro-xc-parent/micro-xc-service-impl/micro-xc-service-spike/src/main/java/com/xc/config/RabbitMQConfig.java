package com.xc.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //定义队列名称
    public static final String QUEUE_NAME_DIRECT_01 = "QUEUE_V1";
    //定义交换机名称
    public static final String EXCHANGE_NAME_02 = "EXCHANGE_V1";
    // 路由键
    public static final String ROUTING_KEY_01 = "routing_key_V1";

    //1 定义队列
    @Bean
    public Queue Direct_01(){
        return new Queue(QUEUE_NAME_DIRECT_01,true);
    }

    //2定义交换机
    @Bean
    public DirectExchange ExDirectExchange_01(){
        //第一个交互机   ，第二个是否持久化    第三个是否自动删除
        return new DirectExchange(EXCHANGE_NAME_02,true,false);
    }


    //3.队列和交互机绑定
    @Bean   //1 队列和 2交换机绑定
    public Binding QueueToDirectExchange_01(){
        return BindingBuilder.bind(Direct_01()).to(ExDirectExchange_01()).with(ROUTING_KEY_01);
    }

}
