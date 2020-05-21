package com.itedu.lesson_06.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个注解
 * 定义注解使用 @interface
 * -- @Target 标识 允许在哪里使用，范围
 * -- @Retention 标识允许反射获取信息
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
    // 传入的值
    String value() default "";
    int classId() default 0;
    String[] array();
}


/**
 * 使用自定义注解
 */
//@AnnotationTest
class AnnDemo {

    private String name;

    //@AnnotationTest(value = "课堂...", classId = 11)
    //@AnnotationTest("课堂...")
    @AnnotationTest(value = "课堂...", classId = 1, array = {"11", "22"})
    public void add() {

    }
}
