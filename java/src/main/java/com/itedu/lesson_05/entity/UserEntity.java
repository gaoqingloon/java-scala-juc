package com.itedu.lesson_05.entity;

import lombok.Data;

@Data
public class UserEntity {

    private String userId;
    private String userName;

    public UserEntity() {
        System.out.println("使用反射技术，执行无参构造函数");
    }

    public UserEntity(String userId) {
        this.userId = userId;
        System.out.println("使用反射技术，执行有参构造函数:" + userId);
    }
}
