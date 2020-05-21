package com.itedu.lesson_06.singleton;

/**
 * 饿汉式 是当class文件被加载的时候，就会被初始化 天生安全
 */
public class SingletonTest2 {
    public static void main(String[] args) {
        Singleton2 s1 = Singleton2.getSingleton();
        Singleton2 s2 = Singleton2.getSingleton();
        System.out.println(s1 == s2);
    }
}


/**
 * 饿汉式
 *      当class文件被加载的时候，就会被初始化 天生安全
 */
class Singleton2 {

    private static final Singleton2 singleton = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getSingleton() {
        return singleton;
    }
}
