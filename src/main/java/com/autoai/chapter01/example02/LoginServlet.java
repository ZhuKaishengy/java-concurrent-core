package com.autoai.chapter01.example02;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhukaishengy
 * @Date: 2020/4/26 16:50
 * @Description:
 */
@Slf4j
public class LoginServlet {

    public static String username;
    public static String password;

    public static void doPost(String u, String p) {
        username = u;
        if ("a".equals(u)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        password = p;
        log.info("username:{},password:{}", username, password);
    }
}
