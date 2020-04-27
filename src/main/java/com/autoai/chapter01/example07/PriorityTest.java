package com.autoai.chapter01.example07;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/27 14:30
 * @Description:
 */
@Slf4j
public class PriorityTest {

    @Test
    public void test1() throws InterruptedException {
        int mainPriority = Thread.currentThread().getPriority();
        // 默认为 NORM_PRIORITY
        log.info("main priority:{}", mainPriority);
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        Thread thread = new Thread(() -> {
            int tpriority = Thread.currentThread().getPriority();
            // 默认为 NORM_PRIORITY
            log.info("thread priority:{}", tpriority);
        });
        thread.start();
        Thread.sleep(5000);
    }

    @Test
    public void test2() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            for (int i = 0; i < 500; i++) {
                log.info("from: th1, count:{}", i);
            }
        });
        Thread thread2 = new Thread(() -> {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            for (int i = 0; i < 500; i++) {
                log.info("from: th2, count:{}", i);
            }
        });
        thread2.start();
        thread1.start();
        Thread.sleep(5000);
    }
}
