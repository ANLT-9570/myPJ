package com.xc.design.handler.impl;

import com.xc.design.handler.GatewayHandler;
import org.springframework.stereotype.Component;

@Component
public class ThirdHandler extends GatewayHandler {

    @Override
    public void service() {
        System.out.println("第三关..............");
    }
}
