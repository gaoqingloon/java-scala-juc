package com.itedu.lesson_06.singleton;

/**
 * 懒汉式设计模式
 * 2019-7-4 14:06:17
 */
public class SingletonTest {
    public static void main(String[] args) {
        // 获取对象
        Singleton s1 = Singleton.getSingleton();
        Singleton s2 = Singleton.getSingleton();
        System.out.println(s1==s2);
    }
}


/**
 * 保证这个类 只能在jvm中有一个实例
 */
class Singleton {

    // 类中的静态属性（对象）
    private static Singleton singleton;

    /**
     * 使外部不能new，构造函数私有化
     * 此时，反射也不能初始化了
     */
    private Singleton() {}

    /**
     * 创建对象
     * 若没有对象则创建，若对象存在则返回
     *
     * 懒汉式：当你需要的时候，我才会去创建，之后都是同一个实例
     * 但 懒汉式 存在线程不安全的问题，可能对创建多个对象，需要加上同步(静态 加类锁)
     * @return
     */
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
