package com.xc.design.StrategyPattern.Impl;

import com.xc.design.StrategyPattern.PayStrategy;

public class AliPayStrategy implements PayStrategy {
    @Override
    public String action() {
        return "这是AliPay支付..";
    }
}
