package com.xc.zookeeper.zkCilentWatch;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class clinet_01 {
    //springboot启动成功后回调执行的方法
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static  String URL = "134.175.244.202";
    private static  Integer TIMEOUT  = 50000;
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(URL, TIMEOUT);
        String parentPath = "/xc-server";
        //监听节点是否发生变化、监听子节点是否发生变化/如果发生变化都可以回调通知
        zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s+":节点发生了变化");
                list.forEach((t)-> System.out.println(t));
            }
        });

        //监听节点value值是否发生变化
        zkClient.subscribeDataChanges(parentPath+"/333", new IZkDataListener() {
            //节点的内容是否发生变化
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("s:"+s+"<-o->"+o);
            }

            //节点是否被删除
            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println(s+"节点被删除");
            }
        });

//        改变节点值
//        zkClient.writeData(parentPath,"999999");

        while (true){}

//        zkClient.close();

    }
}
