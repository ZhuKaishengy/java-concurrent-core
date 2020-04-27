package com.autoai.chapter01.example01;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/26 16:12
 * @Description: 多线程共享数据的情况
 */
@Slf4j
public class MainTest {

    private Integer count = 5;

    private void service1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("thread:{}, count:{}", Thread.currentThread().getName(), count--);
    }

    private synchronized void service2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("thread:{}, count:{}", Thread.currentThread().getName(), count--);
    }

    /**
     * 多线程操作共享变量，i--操作非原子操作
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        Thread myThread1 = new Thread(this::service1);
        Thread myThread2 = new Thread(this::service1);
        Thread myThread3 = new Thread(this::service1);
        Thread myThread4 = new Thread(this::service1);
        Thread myThread5 = new Thread(this::service1);
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
        Thread.sleep(6000);
    }

    /**
     * 使用synchronized加锁
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        Thread myThread1 = new Thread(this::service2);
        Thread myThread2 = new Thread(this::service2);
        Thread myThread3 = new Thread(this::service2);
        Thread myThread4 = new Thread(this::service2);
        Thread myThread5 = new Thread(this::service2);
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
        Thread.sleep(6000);
    }
}
