package com.autoai.chapter01.example03;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/26 17:15
 * @Description:
 */
@Slf4j
public class CurrentThreadTest {

    static class MyThread extends Thread {

        MyThread() throws InterruptedException {
            Thread.sleep(1000);
            log.info("th1:{}", Thread.currentThread().getName());
            log.info("th2:{}", this.getName());
            log.info("th1 alive:{}", Thread.currentThread().isAlive());
            log.info("th2 alive:{}", this.isAlive());
        }

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            log.info("id:{}", id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("th3:{}", Thread.currentThread().getName());
            log.info("th4:{}", this.getName());
            log.info("th3 alive:{}", Thread.currentThread().isAlive());
            log.info("th4 alive:{}", this.isAlive());
        }
    }

    /**
     * 1
     * 2020-04-26 17:35:19 [INFO] th1:main
     * 2020-04-26 17:35:19 [INFO] th2:Thread-0
     * 2
     * 3
     * 2020-04-26 17:35:20 [INFO] th3:B
     * 2020-04-26 17:35:20 [INFO] th4:A
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1");
        MyThread myThread = new MyThread();
        myThread.setName("A");
        System.out.println("2");
        Thread th = new Thread(myThread);
        th.setName("B");
        System.out.println("3");
        th.start();
        Thread.sleep(3000);
    }
}
