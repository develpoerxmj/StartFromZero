package com.xmj.startfromzero.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步锁
 */
public class Synchronization {

    public static void main(String[] args) {
        new Thread(new MyRunnable("One")).start();
        new Thread(new MyRunnable("Two")).start();
    }

    public static class MyRunnable implements Runnable{

        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        private Lock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        @Override
        public void run() {
            /**
             * Synchronized经过编译，会在同步块的前后分别形成monitorenter和monitorexit这个两个字节码指令
             * 在执行monitorenter指令时，首先要尝试获取对象锁。如果这个对象没被锁定，或者当前线程已经拥有了那个对象锁，把锁的计算器加1
             * 相应的，在执行monitorexit指令时会将锁计算器就减1，当计算器为0时，锁就被释放了
             * 如果获取对象锁失败，那当前线程就要阻塞，直到对象锁被另一个线程释放为止
             */

//            synchronized (this){
//                for (int i = 0; i <10 ; i++) {
//                    System.out.println(name + "==" + i);
//                }
//            }

            /**
             * ReentrantLock
             * 重入锁：支持重新进入的锁，表示该锁能够支持一个线程对资源的重复加锁
             */
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(name + "==" + i);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
