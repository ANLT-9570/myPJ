package com.xc.design.handler.service;

import com.xc.design.handler.GatewayHandler;
import com.xc.design.handler.entity.HandlerEntity;
import com.xc.design.handler.mapper.HandlerMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HandlerService {

    @Resource
    private HandlerMapper handlerMapper;

    public GatewayHandler getDBFirstHandler(){
        HandlerEntity firstHandlerEntity = handlerMapper.getFirstHandler();
        if(firstHandlerEntity == null){
            System.out.println("firstHandler........."+firstHandlerEntity);
            return null;
        }
        String handlerBeanId = firstHandlerEntity.getHandlerBeanId();
        if(StringUtils.isEmpty(handlerBeanId)){
            System.out.println("handlerBeanId........."+handlerBeanId);
            return null;
        }
        GatewayHandler firstHandler = com.xc.utils.StringUtils.getBean(handlerBeanId,GatewayHandler.class);
        if(firstHandler == null){
            return null;
        }

        //循环获取下一个节点关联
        String nextHandlerBeanId = firstHandlerEntity.getNextHandlerBeanId();

        GatewayHandler tempNextHandler = firstHandler;

        while (!StringUtils.isEmpty(nextHandlerBeanId)){
            //从容器中获取对象
            GatewayHandler newHandler = com.xc.utils.StringUtils.getBean(nextHandlerBeanId, GatewayHandler.class);
            if(newHandler==null){
                break;
            }
            //从数据库获取信息
            HandlerEntity handlerBeanId1 = handlerMapper.getHandlerBeanId(nextHandlerBeanId);
            if(handlerBeanId1 == null){
                break;
            }
            nextHandlerBeanId = handlerBeanId1.getNextHandlerBeanId();
            //关联下一个handler对象
            tempNextHandler.setNextHandler(newHandler);
            tempNextHandler = newHandler;
        }

        return firstHandler;
    }

}
