package com.xmj.startfromzero;

import com.xmj.startfromzero.thread.StopThread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect(){
//        assertEquals(4, 2 + 2);
        StopThread.MoonRunner moonRunner = new StopThread.MoonRunner();
        Thread thread = new Thread(moonRunner);
        thread.start();
        //主线程休眠10s
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}