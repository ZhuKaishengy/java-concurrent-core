package com.autoai.chapter01.example02;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/26 17:06
 * @Description: 非线程安全的环境
 */
public class MyDemo {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> LoginServlet.doPost("a", "a"));
        Thread thread2 = new Thread(() -> LoginServlet.doPost("b", "b"));
        thread1.start();
        thread2.start();
    }
}
