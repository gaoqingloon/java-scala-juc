package com.itedu.lesson_06.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * 任何类的父类是object类
 */
public class UserEntity extends Object {

    // object类有哪些方法. Object
    // @Override 标识 子类重写父类 检查
    // @Deprecated 标识过时的意思
    // @SuppressWarnings 去除警告
    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public static void add() {

    }

    @SuppressWarnings("all")
    public static void main(String[] args) {
        //new Date().parse("")
        //add();

        //@SuppressWarnings("rawtypes")
        ArrayList<Integer> arrayList = new ArrayList<>();
    }
}
