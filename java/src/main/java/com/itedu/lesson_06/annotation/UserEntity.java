package com.itedu.lesson_06.annotation;

import lombok.Data;

/**
 *
 */
@Data
@SetTable("user_table")
public class UserEntity {
    @SetProperty(name = "user_name", len = 10)
    private String userName;
    @SetProperty(name = "user_age", len = 10)
    private Integer userAge;
}
