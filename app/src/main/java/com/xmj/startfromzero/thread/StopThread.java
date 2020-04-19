package com.xmj.startfromzero.thread;

import java.util.concurrent.TimeUnit;

public class StopThread {

    public static void main(String[] args) throws InterruptedException {
        MoonRunner moonRunner = new MoonRunner();
        Thread thread = new Thread(moonRunner);
        thread.start();
        //主线程休眠10s
        TimeUnit.MILLISECONDS.sleep(10);

        thread.interrupt();
    }

    public static class MoonRunner implements Runnable{

        private long i;

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                i++;
                System.out.println("i= " + i);
            }
            System.out.println("stop");
        }
    }
}
