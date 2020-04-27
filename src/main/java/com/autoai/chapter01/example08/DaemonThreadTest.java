package com.autoai.chapter01.example08;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/27 14:49
 * @Description:
 */
@Slf4j
public class DaemonThreadTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                log.info("haha");
            }
        });
        // 设置守护线程
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
    }
}
