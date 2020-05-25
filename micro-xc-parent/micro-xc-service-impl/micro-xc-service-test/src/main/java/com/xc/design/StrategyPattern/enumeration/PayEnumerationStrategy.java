package com.xc.design.StrategyPattern.enumeration;

public enum  PayEnumerationStrategy {

    APLIPAY("com.xc.design.StrategyPattern.Impl.AliPayStrategy"),
    WECHAT("com.xc.design.StrategyPattern.Impl.WeChatPayStrategy");
    PayEnumerationStrategy(String className){
        this.setClassName(className);
    };

    String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
