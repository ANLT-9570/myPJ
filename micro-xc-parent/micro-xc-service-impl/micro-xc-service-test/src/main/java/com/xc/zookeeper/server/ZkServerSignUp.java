package com.xc.zookeeper.server;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ZkServerSignUp implements ApplicationRunner {

    //springboot启动成功后回调执行的方法
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static  String URL = "134.175.244.202";
    private static  Integer TIMEOUT  = 50000;

    @Value("${server.port}")
    String serverPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /**
         * 创建zk连接
         * 1,连接地址
         * 2,zk超时时间
         * 3,事件通知
         */
        ZooKeeper zooKeeper = new ZooKeeper(URL, TIMEOUT, new Watcher() {
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
        String parentPath = "/xc-server";
        //判断父节点是否存在
        Stat exists = zooKeeper.exists(parentPath, null);
        if(exists == null){
            //创建父节点
            zooKeeper.create(parentPath,"xc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
        //创建字节点
        zooKeeper.create(parentPath+"/"+serverPort,("http://127.0.0.1:"+serverPort).getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println("服务注册成功....."+parentPath);
    }



}
