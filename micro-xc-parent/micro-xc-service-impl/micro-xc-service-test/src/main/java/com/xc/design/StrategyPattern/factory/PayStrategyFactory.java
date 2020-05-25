package com.xc.design.StrategyPattern.factory;

import com.xc.design.StrategyPattern.PayStrategy;
import com.xc.design.StrategyPattern.enumeration.PayEnumerationStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PayStrategyFactory {
    public PayStrategy newInstance(String payType){
        try {
            String className = PayEnumerationStrategy.valueOf(payType).getClassName();
            if(StringUtils.isEmpty(className)){
                System.out.println("为空.....");
                return null;
            }
            PayStrategy payStrategy = (PayStrategy)Class.forName(className).newInstance();
            return payStrategy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
