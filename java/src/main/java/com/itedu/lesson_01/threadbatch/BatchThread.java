package com.itedu.lesson_01.threadbatch;

import com.itedu.lesson_01.threadbatch.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程分批处理数据
 * 2019-6-29 20:14:34
 */
public class BatchThread {

    public static void main(String[] args) {

        // 1. 初始化用户
        List<UserEntity> users = initUser();

        // 2. 定义每一个线程最多跑多少数据
        int userCount = 2;

        // 3. 计算每个线程数，并且计算每个线程跑哪些数据
        List<List<UserEntity>> splitList = ListUtils.splitList(users, userCount);
        // 每次往数据库查询100条，往集合里面放

        for (int i = 0; i < splitList.size(); i++) {
            // 拿到的是每个线程跑多少数据
            List<UserEntity> user = splitList.get(i);
            //System.out.println("i:" + i + "--" + user.toString());

            // 4. 让子线程进行分批异步处理数据
            UserThread userThread = new UserThread(user);
            userThread.start();
        }
    }

    /**
     * 初始化用户信息
     * @return
     */
    public static List<UserEntity> initUser() {
        ArrayList<UserEntity> listUser = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listUser.add(new UserEntity("userId:" + i, "userName:" + i));
        }
        return listUser;
    }
}
