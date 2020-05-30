package com.xc.zookeeper.distributedLock;

public class Test1 {
    public static void main(String[] args) {

        OrderRun orderRun = new OrderRun();
        for(int i =0;i<100;i++){
            Thread thread = new Thread(new OrderRun());
            thread.start();
        }


    }
}
