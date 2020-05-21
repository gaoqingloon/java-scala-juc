package com.itedu.lesson_04;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个定长线程池，支持定时及周期性任务执行
 */
public class NewScheduledThreadPoolTest {

    public static void main(String[] args) {

        // 线程池大小
        ScheduledExecutorService scheduledThreadPool =
                Executors.newScheduledThreadPool(3);

        // schedule 执行定时任务线程池
        scheduledThreadPool.schedule(() ->
                System.out.println("我是2秒钟之后执行..."), 2, TimeUnit.SECONDS);

        // 关闭线程池
        scheduledThreadPool.shutdown();
    }
}
