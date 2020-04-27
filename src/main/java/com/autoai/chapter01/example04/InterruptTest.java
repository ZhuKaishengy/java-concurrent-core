package com.autoai.chapter01.example04;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/26 17:46
 * @Description:
 */
@Slf4j
public class InterruptTest {

    /**
     * 停止不了的线程
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                log.info("num:{}", i);
            }
        });
        thread.start();
        Thread.sleep(10);
        log.info("interrupt...");
        thread.interrupt();
        Thread.sleep(2000);
    }

    /**
     * Thread#interrupted
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                log.info("num:{}", i);
            }
        });
        thread.start();
        log.info("interrupt...");
        thread.interrupt();
        // 当前线程是否终止，指的是test2线程
        log.info("interrupted1:{}", Thread.interrupted());
        // 中断当前线程
        Thread.currentThread().interrupt();
        // 此时结果为true，并清除中断状态
        log.info("interrupted2:{}", Thread.interrupted());
        // 结果为false
        log.info("interrupted3:{}", Thread.interrupted());
    }

    /**
     * thread#isInterrupted
     */
    @Test
    public void test3() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                log.info("num:{}", i);
            }
        });
        thread.start();
        log.info("interrupt...");
        thread.interrupt();
        // 当前线程是否终止，指的是thread线程，结果为true，不清除中断状态
        log.info("interrupted1:{}", thread.isInterrupted());
        // 结果为true
        log.info("interrupted3:{}", thread.isInterrupted());
    }

    /**
     * 使用if判断线程是否中断
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                if (Thread.interrupted()) {
                    log.info("break...");
                    break;
                }
                log.info("num:{}", i);
            }
            log.info("after for cause...");
        });
        thread.start();
        Thread.sleep(10);
        log.info("interrupt...");
        thread.interrupt();
        Thread.sleep(2000);
    }

    /**
     * 异常法中断线程
     * @throws InterruptedException
     */
    @Test
    public void test5() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    if (Thread.interrupted()) {
                        log.info("break...");
                        throw new InterruptedException();
                    }
                    log.info("num:{}", i);
                }
                log.info("after for cause...");
            } catch (InterruptedException e) {
                log.info("thread:{} isInterrupt", Thread.currentThread().getName());
            }
        });
        thread.start();
        Thread.sleep(10);
        log.info("interrupt...");
        thread.interrupt();
        Thread.sleep(2000);
    }

    /**
     * sleep interrupted
     * @throws InterruptedException
     */
    @Test
    public void test6() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                log.info("worker thread begin...");
                Thread.sleep(3000);
                log.info("worker thread end...");
            } catch (InterruptedException e) {
                log.info("thread:{} isInterrupt, error:{}", Thread.currentThread().getName(), e);
            }
        });
        thread.start();
        thread.interrupt();
        Thread.sleep(2000);
        log.info("main :{}", thread.isInterrupted());
    }



}
