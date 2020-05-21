package com.itedu.lesson_06.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 表的别名
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SetTable {
    String value();
}
