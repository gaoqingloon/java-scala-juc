package com.itedu.lesson_01;

/**
 * 使用匿名内部类的方式创建线程
 */
public class ThreadDemo04 {

    public static void main(String[] args) {

        System.out.println("创建线程成功 main");
        // 使用匿名内部类的方式创建线程
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("run() i: " + i);
                }
            }
        }).start();*/
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("run() i: " + i);
            }
        }).start();

        System.out.println("创建线程结束 main");
        for (int i = 0; i < 100; i++) {
            System.out.println("main() i: " + i);
        }
    }
}
