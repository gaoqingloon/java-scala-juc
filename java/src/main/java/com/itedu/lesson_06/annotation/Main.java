package com.itedu.lesson_06.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        // 1. 项目使用注解，肯定会使用到反射，反射应用场景 jdbc、spring ioc、常用框架，一些注解实现
        Class<?> forName = Class.forName("com.itedu.lesson_06.annotation.UserEntity");

//        // getAnnotations() 该类上用到了哪些注解
//        Annotation[] annotations = forName.getAnnotations();
//        for (Annotation annotation : annotations) {
//            System.out.println(annotation);
//        }
//        System.out.println("------------");
        // @com.itedu.lesson_06.annotation.SetTable(value=user_table)

        Field[] declaredFields = forName.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");

        for (int i = 0; i < declaredFields.length; i++) {
            SetProperty setProperty = declaredFields[i].getAnnotation(SetProperty.class);
            String property = setProperty.name();
            stringBuilder.append(property);
            if (i == declaredFields.length - 1) {
                stringBuilder.append(" from ");
            }
            else {
                stringBuilder.append(",");
            }
        }

        // getAnnotation 获取某个注解对象
        SetTable setTable = forName.getAnnotation(SetTable.class);
        //System.out.println(setTable.value());  // user_table

        // 表的名称
        String tableName = setTable.value();
        stringBuilder.append(tableName);
        stringBuilder.append(";");

        System.out.println(stringBuilder.toString());

        // 生成orm 框架sql语句
    }
}
