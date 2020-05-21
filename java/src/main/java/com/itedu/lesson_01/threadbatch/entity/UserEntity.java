package com.itedu.lesson_01.threadbatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserEntity {

    private String userName;
    private String userId;
}
