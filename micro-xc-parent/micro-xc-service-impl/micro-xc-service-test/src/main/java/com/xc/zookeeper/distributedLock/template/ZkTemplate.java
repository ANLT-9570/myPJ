package com.xc.zookeeper.distributedLock.template;

import com.xc.zookeeper.distributedLock.design.impl.AbstractTemplateLock;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;

public class ZkTemplate extends AbstractTemplateLock {
    //zk地址
    private static  String URL = "134.175.244.202";
    //超时时间
    private static  Integer TIMEOUT  = 50000;
    //创建zk连接
    private  ZkClient zkClient = new ZkClient(URL, TIMEOUT);
    //共同的临时节点
    private static String LOCAL = "/lockPath";

    private  CountDownLatch countDownLatch = null;

    @Override
    protected boolean tryOut() {
        try {
            zkClient.createEphemeral(LOCAL);
            return true;
        }catch (Exception e){
            //如果已经存在返回false
            return false;
        }
//        String flag = zkClient.create(LOCAL, LOCAL.getBytes(), CreateMode.EPHEMERAL);
    }

    @Override
    protected void waitLock() throws InterruptedException {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (s != null) {//节点被删除减1
                    countDownLatch.countDown();
                }
            }
        };
        //使用事件监听，，监听lockPath是否被删除如果被删除的情况下，重新进入到获取锁
        zkClient.subscribeDataChanges(LOCAL, iZkDataListener);

        if(countDownLatch == null){
            countDownLatch = new CountDownLatch(1);
        }
        countDownLatch.await();//如果计数器不为0继续等待
        //移除事件
        zkClient.unsubscribeChildChanges(LOCAL, (IZkChildListener) iZkDataListener);
    }

    @Override
    protected void unImplLock() {
        if(zkClient != null){
            zkClient.close();
            System.out.println(Thread.currentThread().getName()+"释放锁>>>>>>>>>>>>>");
        }
    }
}
