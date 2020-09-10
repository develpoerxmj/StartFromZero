package com.xmj.startfromzero;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect(){
//        assertEquals(4, 2 + 2);
//        StopThread.MoonRunner moonRunner = new StopThread.MoonRunner();
//        Thread thread = new Thread(moonRunner);
//        thread.start();
//        //主线程休眠10s
//        try {
//            TimeUnit.MILLISECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        thread.interrupt();


//        for (int i = 0; i < 10; i++) {
//            log(i);
//        }
//        Queue<Integer> queue = new ArrayDeque<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        for (Integer integer : queue) {
//            System.out.println(integer + "");
//        }
//
//        Bean bean1 = new Bean();
//        bean1.atomicInteger.incrementAndGet();
//
//        Bean bean2 = new Bean();
//        bean2.setAtomicInteger(bean1);
//        bean2.atomicInteger.incrementAndGet();
//
//        System.out.println("Bean1" + bean1.atomicInteger.get());
//        System.out.println("Bean2" + bean2.atomicInteger.get());
//        System.out.println("file\"; filename=\"");

        //原始类型
        Box box = new Box();
        //参数化类型
        Box<Integer> integerBox = new Box<>();
    }
//
//    private void log(int i) {
//        if (i == 5)return;
//        System.out.println(i + "");
//    }

}