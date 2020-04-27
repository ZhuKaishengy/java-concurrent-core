package com.autoai.chapter01.example05;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/27 11:13
 * @Description:
 */
@Slf4j
public class SuspendResumeTest {

    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                log.info("haha{}", i);
            }
        });
        thread.start();
        Thread.sleep(100);
        log.info("worker线程暂停。。。");
        thread.suspend();
        Thread.sleep(3000);
        thread.resume();
        log.info("worker线程恢复。。。");
        Thread.sleep(3000);
    }

    public static synchronized void method() {
        try {
            log.info("thread:{}", Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步对象被独占
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            SuspendResumeTest.method();
        });
        thread1.start();
        Thread.sleep(1000);
        Thread thread2 = new Thread(() -> {
            SuspendResumeTest.method();
        });
        thread2.start();
        log.info("worker1线程暂停。。。");
        thread1.suspend();
        Thread.sleep(8000);
    }

    /**
     * println方法被独占
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            long i = 0;
            while (true) {
                System.out.println("hahaha" + i);
                i ++;
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.suspend();
        System.out.println("main end...");
    }

    String key,value;

    class MyThread extends Thread {

        String k1,v1;

        public MyThread(String k, String v) {
            k1 = k;
            v1 = v;
        }

        @Override
        public void run() {
            key = this.k1;
            if (key.equals("2")) {
                this.suspend();
            }
            value = this.v1;
        }
    }

    /**
     * 同步对象不一致问题
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        Thread th1 = new MyThread("1","1");
        th1.start();
        Thread.sleep(1000);
        Thread th2 = new MyThread("2","2");
        th2.start();
        Thread.sleep(1000);
        log.info("key:{}, value:{}", key, value);
    }

}
