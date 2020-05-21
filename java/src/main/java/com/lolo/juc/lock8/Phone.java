package com.lolo.juc.lock8;

import java.util.concurrent.TimeUnit;

public class Phone {

    // 1. 属性
    // 2. 方法
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("--sendEmail--");
    }

    public static synchronized void getSMS() throws Exception {
        System.out.println("--getSMS--");
    }

    public void getHello() {
        System.out.println("--getHello--");
    }
}
