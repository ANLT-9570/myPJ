package com.xc.design.handler.impl;

import com.xc.design.handler.GatewayHandler;
import org.springframework.stereotype.Component;

@Component
public class FirstHandler extends GatewayHandler {

//    SecondHandler secondHandler;

    @Override
    public void service() {
        System.out.println("第一关............");
        nextService();
    }

//    public void setNextHandler(SecondHandler secondHandler){
//        this.secondHandler=secondHandler;
//    }
}
