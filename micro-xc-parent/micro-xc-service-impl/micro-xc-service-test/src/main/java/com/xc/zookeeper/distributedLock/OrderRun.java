package com.xc.zookeeper.distributedLock;

import com.xc.zookeeper.distributedLock.template.ZkTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderRun implements Runnable {
    private static int count;
    private Lock lock = new ReentrantLock();

    private com.xc.zookeeper.distributedLock.design.Lock zkLock = new ZkTemplate();

    @Override
    public void run() {
        getNumber();
    }
    public void getNumber(){//synchronized

        try{
//            lock.lock();
            zkLock.getLock();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd-HH-mm");
            String format = simpleDateFormat.format(new Date());
            System.out.println(Thread.currentThread().getName()+"获取的number--->:"+format+"-"+ count);
            ++count;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            zkLock.unLock();
//            lock.unlock();
        }

    }


}
