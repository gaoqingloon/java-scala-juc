package com.itedu.lesson_01.threadbatch;

import com.itedu.lesson_01.threadbatch.entity.UserEntity;

import java.util.List;

/**
 * 每个线程分批多少数据
 */
public class UserThread extends Thread {

    private List<UserEntity> listUser;

    public UserThread(List<UserEntity> listUser) {
        this.listUser = listUser;
    }

    @Override
    public void run() {
        for (UserEntity userEntity : listUser) {
            System.out.println("name: " + getName() + "--" + userEntity.toString());
            // 调用第三方的短信接口
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
