package com.xc.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class zookeeper_01 {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String url = "134.175.244.202";
        Integer timeOut  = 5000;

        /**
         * 创建zk连接
         * 1,连接地址
         * 2,zk超时时间
         * 3,事件通知
         */
        ZooKeeper zooKeeper = new ZooKeeper(url, timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                Event.KeeperState keeperState = watchedEvent.getState();
                if(keeperState == Event.KeeperState.SyncConnected){
                    System.out.println("zk连接成功.......");
                    countDownLatch.countDown();
                }
            }
        });
        System.out.println("正在连接zk.......");
        countDownLatch.await();
        System.out.println("开始创建节点....");
        /**
         * 创建节点
         * 1，路径名称
         * 2，节点value
         * 3，节点权限（acl）
         * 4，节点类型（临时和持久节点）
         */
        String result = zooKeeper.create("/xc/xc1", "xc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(result);

        zooKeeper.close();
    }
}
