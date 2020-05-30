package com.xc.zookeeper.ACL;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class acl_01 {
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
        System.out.println("正在连接zk.......");
        countDownLatch.await();
        System.out.println("开始创建节点....");

        //创建账号权限admin可以实现读写操作
        Id digest1 = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:123"));
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, digest1);

        //创建guest用户只应许做读操作
        Id digest2 = new Id("digest", DigestAuthenticationProvider.generateDigest("guest:123"));
        ACL acl2 = new ACL(ZooDefs.Perms.READ, digest2);

        //添加账号
        List<ACL> acls = new ArrayList<>();
        acls.add(acl1);
        acls.add(acl2);

        /**
         * 创建节点
         * 1，路径名称
         * 2，节点value
         * 3，节点权限（acl）
         * 4，节点类型（临时和持久节点）
         */
        String result = zooKeeper.create("/test", "test_xc".getBytes(), acls, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(result);

        zooKeeper.close();
    }
}
