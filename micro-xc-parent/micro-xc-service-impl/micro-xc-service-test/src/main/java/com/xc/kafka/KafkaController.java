package com.xc.kafka;

import com.xc.result.Result;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class KafkaController {

//    @Resource
//    KafkaTemplate<String,Object> kafkaTemplate;
//
//    public Result k(){
//        kafkaTemplate.send("test","132","6666");
//        return Result.successResult();
//    }
//
//    @KafkaListener(topics = "test")
//    public void receive(ConsumerRecord<?,?> consumerRecord){
//        System.out.println(consumerRecord.topic()+","+consumerRecord.key()+""+consumerRecord.partition());
//    }

}
