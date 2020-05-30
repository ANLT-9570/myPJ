package com.xc.zookeeper.distributedLock.design;

public interface Lock {

    /**
     * 获取锁
     */
    void getLock() throws InterruptedException;

    /**
     * 释放锁
     */
    void unLock();

}
