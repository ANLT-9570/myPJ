package com.xc.design.StrategyPattern.Impl;

import com.xc.design.StrategyPattern.PayStrategy;

public class WeChatPayStrategy implements PayStrategy {

    @Override
    public String action() {
        return "WeChat支付方式";
    }

}
