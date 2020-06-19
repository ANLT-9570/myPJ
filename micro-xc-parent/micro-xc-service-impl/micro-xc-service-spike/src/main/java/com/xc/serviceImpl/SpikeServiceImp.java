package com.xc.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.xc.SpikeService;
import com.xc.mapper.OrderMapper;
import com.xc.mapper.SpikeMapper;
import com.xc.mapper.entity.order;
import com.xc.mapper.entity.seckill;
import com.xc.produce.SpikeProduce;
import com.xc.redis.RedisUtil;
import com.xc.result.Result;
import com.xc.token.GenerateToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class SpikeServiceImp implements SpikeService {

    @Resource
    SpikeMapper spikeMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    private RedisUtil redisUtil;
    @Resource
    GenerateToken generateToken;

    @Autowired
    SpikeProduce spikeProduce;

    //每秒钟生成一个
    RateLimiter rateLimiter = RateLimiter.create(1);

    @Override
    public Result spike(String phone, Long seckillId) {
        if(StringUtils.isEmpty(phone)){
            return Result.failureResult("808","不能为空");
        }
        if(seckillId == null){
            return Result.failureResult("808","不能为空");
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("seckill_id",seckillId);
        seckill seckill = spikeMapper.selectOne(wrapper);
        if(seckill == null){
            return Result.failureResult("707","没有该商品");
        }
        // .用户评率限制 放到网关
        Boolean aBoolean = redisUtil.setNx(phone, seckillId.toString(), 10L);
        if(!aBoolean){
            return Result.failureResult("606","访问次数过多");
        }

        //3.修改数据库对应的库存
        int i = spikeMapper.inventoryDeductionWithVersion(seckillId,seckill.getVersion());
//        int i = spikeMapper.inventoryDeduction(seckillId);
        if(i<1){
            System.out.println(".....失败...");
            return Result.failureResult("888","亲，消后重试..");
        }
        //4.添加秒杀成功订单    使用mq异步
        order order = new order();
        order.setPhone(phone);
        order.setStatus(1);
        order.setSeckill(seckillId);
        int insert = orderMapper.insert(order);
        if(insert<1){
            System.out.println(".....失败...*-*-*-");
            return Result.failureResult("888","亲，消后重试..");
        }
        System.out.println("成功....");
        return Result.successResult("秒杀成功...");
    }

    @Override
    @HystrixCommand(fallbackMethod = "backHandler")
    public Result spike_v2(String phone, Long seckillId) {
        log.info(">>>>线程池名称:{}",Thread.currentThread().getName());

        if(StringUtils.isEmpty(phone)){
            return Result.failureResult("808","不能为空");
        }
        if(seckillId == null){
            return Result.failureResult("808","不能为空");
        }
        //限流操作
        boolean b = rateLimiter.tryAcquire(0, TimeUnit.SECONDS);
        if(!b){
            return Result.failureResult("33","当前人数过多消耗重试...");
        }

        //从redis中获取对应的秒杀token
        String listKeyToken = generateToken.getListKeyToken(seckillId.toString());
        if(StringUtils.isEmpty(listKeyToken)){
            System.out.println("***********......");
            return Result.failureResult("888","商品搜空...");
        }
        //获取成功后，异步存放到mq中实现商品的库存修改
        sendSeckill(phone,seckillId);

        return Result.failureResult("888","正在排队...");
    }

    public Result backHandler(String phone, Long seckillId){
        System.out.println("人数过多..");
        return Result.successResult("人数过多..");
    }

    @Async
    protected void sendSeckill(String phone, Long seckillId){
        Map<String,Object> map = new HashMap<>();
        map.put("seckillId",seckillId);
        map.put("phone",phone);
        spikeProduce.send(new Gson().toJson(map));
    }

    //采用redis数据库类型为list类型key为商品库存id list多个秒杀token
    @Override
    public Result addSpikeToken(Long seckillId, Integer tokenQuantity) {
        log.info(">>>>线程池名称:{}",Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName());
        if(seckillId == null){
            return Result.failureResult("888","seckillId不能为空");
        }
        if(tokenQuantity == null){
            return Result.failureResult("888","tokenQuantity不能为空");
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("seckill_id",seckillId);
        seckill seckill = spikeMapper.selectOne(wrapper);
        if(seckill==null){
            return Result.failureResult("888","没有该商品");
        }
        //使用多线程异步生产令牌
        createSpikeToken(seckillId,tokenQuantity);
        return Result.successResult("令牌正在生产中....");
    }

    @Async
    protected void createSpikeToken(Long seckillId, Integer tokenQuantity) {
        generateToken.creteListToken("seckill_",seckillId+"",tokenQuantity);
    }

}
