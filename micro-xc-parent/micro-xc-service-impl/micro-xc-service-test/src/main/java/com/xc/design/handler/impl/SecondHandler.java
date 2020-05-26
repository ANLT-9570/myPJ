package com.xc.design.handler.impl;

import com.xc.design.handler.GatewayHandler;
import org.springframework.stereotype.Component;

@Component
public class SecondHandler extends GatewayHandler {

//    ThirdHandler thirdHandler;

    @Override
    public void service() {
        System.out.println("第二关................");
        nextService();
//        thirdHandler.service();
    }

//    public void setNextHandler(ThirdHandler thirdHandler){
//        this.thirdHandler=thirdHandler;
//    }

}
