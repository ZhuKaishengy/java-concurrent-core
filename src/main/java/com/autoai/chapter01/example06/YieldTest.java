package com.autoai.chapter01.example06;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/27 14:17
 * @Description:
 */
@Slf4j
public class YieldTest {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            long start = Instant.now().toEpochMilli();
            for (int i = 0; i < 100; i++) {
                log.info("count:{}", i);
            }
            long end = Instant.now().toEpochMilli();
            log.info("duration1:{}", end - start);
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            long start = Instant.now().toEpochMilli();
            for (int i = 0; i < 100; i++) {
                log.info("count:{}", i);
                Thread.yield();
            }
            long end = Instant.now().toEpochMilli();
            log.info("duration2:{}", end - start);
        });
        thread2.start();
    }
}
