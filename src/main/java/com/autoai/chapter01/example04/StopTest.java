package com.autoai.chapter01.example04;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/27 10:27
 * @Description:
 */
@Slf4j
public class StopTest {

    /**
     * stop为停止，并非中断
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                log.info("haha");
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.stop();
        Thread.sleep(2000);
        log.info("thread.isInterrupted:{}", thread.isInterrupted());
    }

    /**
     * java.lang.ThreadDeath异常
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    log.info("haha");
                }
            } catch (ThreadDeath threadDeath) {
                log.error("error occur:{}", threadDeath);
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.stop();
        Thread.sleep(2000);
    }

    private static String key = "haha";
    private static String value = "haha";


    public static synchronized void prop(String k, String v) throws InterruptedException {
        key = k;
        Thread.sleep(2000);
        value = v;
        log.info("key:{}, value:{}", key, value);
    }

    /**
     * stop()释放锁导致数据不一致
     */
    @Test
    public void stopThrowLock() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                StopTest.prop("zks", "zks");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.stop();
        log.info("key:{}, value:{}", key, value);
    }
}
