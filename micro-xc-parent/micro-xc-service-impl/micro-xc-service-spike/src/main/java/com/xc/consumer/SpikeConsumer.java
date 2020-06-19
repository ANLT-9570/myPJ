package com.xc.consumer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.xc.config.RabbitMQConfig;
import com.xc.mapper.OrderMapper;
import com.xc.mapper.SpikeMapper;
import com.xc.mapper.entity.order;
import com.xc.mapper.entity.seckill;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Component
public class SpikeConsumer {

    @Resource
    SpikeMapper spikeMapper;

    @Resource
    OrderMapper orderMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_DIRECT_01)
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), "utf-8");
        System.out.println(messageId+"---"+msg);
//        if(true) return;
        Gson gson = new Gson();

        Map map = gson.fromJson(msg, Map.class);
        //秒杀id
        Object seckillId = map.get("seckillId");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("seckill_id",seckillId);
        seckill seckill = spikeMapper.selectOne(wrapper);
        if(seckill == null){
            System.out.println(">>>>>>>>>商品信息不存在--"+seckillId);
            return;
        }

        int version = seckill.getVersion();
        int i = spikeMapper.inventoryDeductionWithVersion(seckill.getSeckillId(), version);
        if(i<=0){
            System.out.println(">>>>>>>>>修改库存失败--"+seckillId+"***"+i);
            return;
        }

        order order = new order();
        order.setSeckill(seckill.getSeckillId());
        order.setPhone(map.get("phone").toString());
        order.setStatus(1);
        int insert = orderMapper.insert(order);
        if(insert<=0){
            System.out.println("订单失败..........");
            return;
        }
        //手动ack
//        Long deliveryTag  =(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手动签收
//        channel.basicAck(deliveryTag,false);
        System.out.println(">>>>>>>>>>>成功》》》");
    }

}
