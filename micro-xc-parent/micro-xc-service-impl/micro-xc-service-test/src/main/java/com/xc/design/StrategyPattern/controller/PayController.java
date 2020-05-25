package com.xc.design.StrategyPattern.controller;

import com.xc.design.StrategyPattern.context.PayContextStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @Autowired
    PayContextStrategy payContextStrategy;
    @GetMapping("/pay")
    public String process(String payType){
        return payContextStrategy.process(payType);
    }
}
