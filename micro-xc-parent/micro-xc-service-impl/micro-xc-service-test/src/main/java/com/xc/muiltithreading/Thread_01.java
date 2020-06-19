package com.xc.muiltithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Thread_01 {
    public static void main(String[] args) {
//        cacheThread();
            fixedThread();

    }

    public static void cacheThread(){
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i = 0;i<10;i++){
            final int f = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"-任务-"+f);
                }
            });
        }
    }

    public static void fixedThread(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            final int a = i;
            executorService.execute(()->System.out.println(Thread.currentThread().getName()+"-mission-"+a));
        }


    }

}
