package com.xc.design.handler.factory;

import com.xc.design.handler.impl.FirstHandler;
import com.xc.design.handler.impl.SecondHandler;
import com.xc.design.handler.impl.ThirdHandler;

/**
 * 基于工厂模式
 */

public class FactoryHandler {
    public static FirstHandler getFirstHandler(){
        //第一关
        FirstHandler firstHandler = new FirstHandler();

        //第二关
        SecondHandler secondHandler = new SecondHandler();
        firstHandler.setNextHandler(secondHandler);
        //第三关
        ThirdHandler thirdHandler = new ThirdHandler();
        secondHandler.setNextHandler(thirdHandler);

        return firstHandler;
    }
    //采用数据库形式...
}
