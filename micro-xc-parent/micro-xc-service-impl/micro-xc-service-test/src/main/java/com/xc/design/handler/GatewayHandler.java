package com.xc.design.handler;

public abstract class GatewayHandler {

    /**
     * 共同的行为
     */
    public abstract void service();

    /**
     * 下一个handler
     */
    public GatewayHandler nextHandler;

    /**
     * 设计下一个handler
     * @param nextHandler
     */
    public void setNextHandler(GatewayHandler nextHandler){
        this.nextHandler = nextHandler;
        System.out.println(this.nextHandler.toString() +"------*****"+ nextHandler.toString());
    }

    /**
     * 执行下一个handler
     */
    public void nextService(){
        if(nextHandler != null){
            nextHandler.service();
        }
    }

}
