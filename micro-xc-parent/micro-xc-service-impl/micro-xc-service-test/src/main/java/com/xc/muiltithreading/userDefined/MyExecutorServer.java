package com.xc.muiltithreading.userDefined;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyExecutorServer {

    /**
     * 一直保持运行的线程数
     */
    private List<WorkerThread> workerThreads;

    /**
     * 执行任务队列容器
     */
    private BlockingQueue<Runnable> taskRunnable;

    private Boolean isWorking = true;

    public MyExecutorServer(int workerThreads,int taskRunnable){
        this.taskRunnable = new LinkedBlockingDeque<>(taskRunnable);
        this.workerThreads = new ArrayList<>();

        //提前预热创建固定的线程
        for(int i = 0; i<workerThreads;i++){
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            this.workerThreads.add(workerThread);
        }

    }

    public void executor(Runnable runnable){
        this.taskRunnable.offer(runnable);
    }

    public void shutdown(){
        isWorking = false;
    }

    class WorkerThread extends Thread{
        @Override
        public void run() {
            while (isWorking || taskRunnable.size()>0){
                //正在运行的线程不断从队列中获取任务，获取到就执行任务
                Runnable task = taskRunnable.poll();
                if(task != null){
                    task.run();
                }
            }

        }
    }

    public static void main(String[] args) {
        MyExecutorServer myExecutorServer = new MyExecutorServer(5,1000);

        for(int i=0;i<100;i++){
            int finalI = i;
            myExecutorServer.executor(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"----"+ finalI);
                }
            });
        }


        myExecutorServer.shutdown();

    }

}
