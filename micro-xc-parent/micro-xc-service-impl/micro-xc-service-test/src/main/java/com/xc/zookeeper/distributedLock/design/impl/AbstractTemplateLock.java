package com.xc.zookeeper.distributedLock.design.impl;

import com.xc.zookeeper.distributedLock.design.Lock;

public abstract class AbstractTemplateLock implements Lock {

    @Override
    public void getLock() throws InterruptedException {
        //模板方法定义共同抽象的骨架
        if(tryOut()){
//            System.out.println(Thread.currentThread().getName()+"");
        }else{
            //开始实现等待
            waitLock();//时间监听

            //重新获取
            getLock();
        }
    }

    /**
     * 获取锁
     * @return
     */
    protected abstract void waitLock() throws InterruptedException;

    /**
     * 等待锁
     * @return
     */
    protected abstract boolean tryOut();

    /**
     * 释放锁
     * @return
     */
    protected abstract void unImplLock();

    @Override
    public void unLock() {
        unImplLock();
    }

}
