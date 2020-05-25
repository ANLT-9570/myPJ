package com.xc.design.StrategyPattern.context;

import com.xc.design.StrategyPattern.PayStrategy;
import com.xc.design.StrategyPattern.factory.PayStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayContextStrategy {
    @Autowired
    private PayStrategyFactory payStrategyFactory;
    public String process(String payType){
        PayStrategy payStrategy = payStrategyFactory.newInstance(payType);
        if(payStrategy==null){
            System.out.println("null了............");
            return "null";
        }
        return payStrategy.action();
    }
}
