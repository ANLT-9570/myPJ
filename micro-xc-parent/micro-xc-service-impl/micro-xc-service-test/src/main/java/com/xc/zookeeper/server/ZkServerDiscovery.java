package com.xc.zookeeper.server;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class ZkServerDiscovery {

    //springboot启动成功后回调执行的方法
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static  String URL = "134.175.244.202";
    private static  Integer TIMEOUT  = 50000;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
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
        //获取该节点下的所有子节点
        List<String> children = zooKeeper.getChildren(parentPath, null);
        for(String child:children){//遍历子节点
            //子节点的完整路径
            String childPath = parentPath+"/"+child;
            //获取子节点的服务
            byte[] data = zooKeeper.getData(childPath, null, new Stat());
            System.out.println("服务地址："+new String(data));
        }
    }



}
