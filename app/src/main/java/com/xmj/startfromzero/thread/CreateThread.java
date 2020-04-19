package com.xmj.startfromzero.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateThread {

    /**
     * 1、继承Thread,重写run()方法，调用start
     */
    public static class ThreadOne extends Thread{
        @Override
        public void run() {
            // TODO: 2020/4/19
        }
    }

    /**
     * 2、实现Runnable接口，和run()方法
     */
    public static class ThreadTwo implements Runnable{
        @Override
        public void run() {
            // TODO: 2020/4/19
        }
    }

    /**
     * 实现Callable接口，重写call()方法
     */
    public static class ThreadThree implements Callable<String>{

        @Override
        public String call() throws Exception {
            // TODO: 2020/4/19
            return null;
        }
    }


    public static void main(String[] args) {
        //1
        ThreadOne one = new ThreadOne();
        one.start();
        //2
        new Thread(new ThreadOne()).start();
        //3 线程池
        ThreadThree three = new ThreadThree();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(three);

    }
}
