package com.itedu.lesson_03;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadJoin t1 = new ThreadJoin();
        ThreadJoin t2 = new ThreadJoin();
        t1.start();
        t1.join();  // 让其他线程等待，只有当前线程执行完毕，才会释放资格
        // t1.join(100)  // 只等待100ms
        t2.start();
        for (int i = 0; i < 40; i++) {
            System.out.println("main---" + i);
        }
    }
}


class ThreadJoin extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "---" + i);
        }
    }
}
