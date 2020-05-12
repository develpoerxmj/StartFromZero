package com.xmj.startfromzero.thread;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;


/**
 * 阻塞队列
 *
 * 队列满时，生产者线程阻塞
 * 队列空时，消费者线程阻塞
 */
public class BlockingQueueTest {

    /**
     * 生产者——消费者
     */

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    /**
     * 阻塞队列
     */
    ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(queueSize);

    public static void main(String[] args) {
        BlockingQueueTest test = new BlockingQueueTest();
        Thread pool = new Thread(test.new ThreadPool());
        Thread put = new Thread(test.new ThreadPut());
        pool.start();
        put.start();


        Executors.newSingleThreadExecutor();
    }

    //消费者线程(拿走)
    public class ThreadPool implements Runnable{

        @Override
        public void run() {
            while (true){
//                synchronized (queue){
//                    while (queue.size() == 0){
//                        System.out.println("队列为空");
//                        try {
//                            queue.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            queue.notify();
//                        }
//                    }
////                  queue.peek();//得到队首元素（不会删除）
//
//                    //每次移走队首元素
//                    System.out.println(queue.poll());
//                    queue.notify();
//                }

                try {
                    System.out.println(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //生产者线程
    public class ThreadPut implements Runnable{

        @Override
        public void run() {
            while (true){
//                synchronized (queue){
//                    while (queue.size() == queueSize){
//                        try {
//                            queue.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            queue.notify();
//                        }
//                    }
//                    queue.add((int) (Math.random() * 10));
//                    queue.notify();
//                }
                try {
                    blockingQueue.put((int) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
