package com.itedu.lesson_04;

/**
 * volatile关键字，强制刷新到主内存
 * 解决线程之间可见性的问题
 */
public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo t1 = new ThreadVolatileDemo();
        // 运行在子线程的本地内存 flag=true
        t1.start();
        Thread.sleep(300);

        // 运行在主线程的本地内存 flag=false
        t1.isRun(false);
        System.out.println("flag: " + t1.flag);

        /*
        子线程开始执行...
        子线程结束执行...
        flag: false
         */
    }
}


class ThreadVolatileDemo extends Thread {

    // 主内存中的变量
    // 本地线程没有及时把flag刷新到主内存中
    // 解决办法：加volatile关键字，强制刷新到主内存
    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("子线程开始执行...");
        while (flag) {

        }
        System.out.println("子线程结束执行...");
    }

    public void isRun(boolean flag) {
        this.flag = flag;
    }
}
