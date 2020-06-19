package com.xc.produce;

import com.xc.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@Slf4j
public class SpikeProduce {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //消息发送后回调这个方法
//    如果消息没有到exchange,则confirm回调,ack=false
//    如果消息到达exchange,则confirm回调,ack=true
//    只确认消息是否正确到达 Exchange 中。
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        //采用应答机制
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            String id = correlationData.getId();
            System.out.println("消息id:"+id);
            if(ack){
                System.out.println("消息发送成功..");
            }else{
                //重试..
                send(id);
                System.out.println("消息发送失败..");
            }
            System.out.println(cause);
        }
    };
    //消息发送到没有的队列或者路由键就会回调这个方法
//    exchange到queue成功,则不回调return
//    exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
//    消息没有正确到达队列时触发回调，如果正确到达队列不执行。
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int code, String cause, String Exchange, String routingKey) {
            System.out.println(message);
            log.info("发送的消息:{},响应码:{},原因:{},交换机:{},路由键:{}",message,code,code,Exchange,routingKey);
        }
    };


    @Transactional
    public void send(String o){
        System.out.println("**"+o);
        String s = UUID.randomUUID().toString().replace("-", "");
        //封装消息
//        new Message();
        //构建回调返回的数据
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData(s);
        Message message = MessageBuilder.withBody(o.getBytes()).build();
        message.getMessageProperties().setMessageId(s);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME_02,RabbitMQConfig.ROUTING_KEY_01,message,correlationData);
    }

}
