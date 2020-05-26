package com.xc.design.handler.controller;

import com.xc.design.handler.GatewayHandler;
import com.xc.design.handler.factory.FactoryHandler;
import com.xc.design.handler.impl.FirstHandler;
import com.xc.design.handler.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HandlerController {

    @Autowired
    HandlerService handlerService;

    @GetMapping("/handlers")
    public void exec(){
//        FirstHandler firstHandler = FactoryHandler.getFirstHandler();
//        firstHandler.service();
        GatewayHandler dbFirstHandler = handlerService.getDBFirstHandler();
        dbFirstHandler.service();
    }

    @GetMapping("/handlers2")
    public void exec2(){
        GatewayHandler dbFirstHandler = handlerService.getDBFirstHandler();
        dbFirstHandler.nextService();
        System.out.println(dbFirstHandler);
    }
}
