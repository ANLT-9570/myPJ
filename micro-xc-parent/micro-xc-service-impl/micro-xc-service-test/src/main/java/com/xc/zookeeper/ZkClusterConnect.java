package com.xc.zookeeper;


import org.I0Itec.zkclient.ZkClient;

public class ZkClusterConnect {

    private static String addr = "134.175.244.202:2181,120.79.42.229:2181";
    private static Integer timeOut  = 5000;

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(addr,timeOut);
//        zkClient.createEphemeral("/t1");
        zkClient.createPersistent("/t2");
        zkClient.close();
    }
}
