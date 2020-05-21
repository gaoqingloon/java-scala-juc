package com.itedu.lesson_01;

public class ThreadDemo05 {

    public static void main(String[] args) {

        //new DemoThread().start();
        //new DemoThread().start();
        DemoThread demoThread = new DemoThread();
        demoThread.setName("线程1");
        DemoThread demoThread2 = new DemoThread();
        demoThread2.setName("线程2");
        demoThread.start();
        demoThread2.start();
    }
}


class DemoThread extends Thread {

    /**
     * 在run方法中不能抛出异常，只能try catch
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("getId(): " + getId() +
                    ", getName(): " + getName() + ", i: " + i);
            // 单位毫秒
            try {
                // sleep作用让当前线程从运行状态变为休眠状态，如果时间到期会变为运行状态
                // sleep不能释放锁，多线程之间实现同步   wait可以释放锁
                Thread.sleep(1000);
                // 获取到线程的ID，ID是多线程随机进行分配不重复主键
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
