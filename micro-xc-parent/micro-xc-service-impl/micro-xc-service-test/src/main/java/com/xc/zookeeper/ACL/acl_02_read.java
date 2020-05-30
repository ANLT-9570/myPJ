package com.xc.zookeeper.ACL;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class acl_02_read {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException, NoSuchAlgorithmException {
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
        countDownLatch.await();

        //设置zk连接账号
        zooKeeper.addAuthInfo("digest","guest:123".getBytes());

        //获取节点内容
        byte[] bytes = zooKeeper.getData("/test0000000004", null, new Stat());///test0000000004
        System.out.println(new String(bytes));

    }
}
