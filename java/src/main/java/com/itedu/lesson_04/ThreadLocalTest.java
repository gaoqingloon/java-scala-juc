package com.itedu.lesson_04;

/**
 * 本地线程demo
 * 每个线程单独一份，不会全局共享
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        Res res = new Res();
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo(res);
        Thread t1 = new Thread(threadLocalDemo);
        Thread t2 = new Thread(threadLocalDemo);
        Thread t3 = new Thread(threadLocalDemo);
        t1.start();
        t2.start();
        t3.start();

//        Res res1 = new Res();
//        Res res2 = new Res();
//        Res res3 = new Res();
//        Thread t1 = new Thread(new ThreadLocalDemo(res1));
//        Thread t2 = new Thread(new ThreadLocalDemo(res2));
//        Thread t3 = new Thread(new ThreadLocalDemo(res3));
//        t1.start();
//        t2.start();
//        t3.start();
    }
}


/**
 * 资源类
 */
class Res {

    //private int count = 0;
    /**
     * 设置本地局部变量，和其他线程局部变量隔离开，互补影响
     */
    private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            // 设置当前线程局部变量 初始化值
            return 0;
        }
    };

    /**
     * 生成订单号
     * @return
     */
    public int getNum() {
        //count++;
        //return count;
        int count = this.count.get() + 1;
        this.count.set(count);
        return count;
    }
}


class ThreadLocalDemo implements Runnable {

    private Res res;
    public ThreadLocalDemo(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + "---i: " + i + ", number: " + res.getNum());
        }
    }
}
