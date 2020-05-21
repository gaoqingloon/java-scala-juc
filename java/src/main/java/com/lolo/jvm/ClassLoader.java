package com.lolo.jvm;

/**
 * 双亲委派机制 :
 *      Bootstrap 启动类加载器
 *      ExtensionClassLoader 扩展类加载器
 *      ApplicationClassLoader 应用程序类加载器
 *
 * sun.misc.Launcher Java虚拟机程序的入口
 *
 * 沙箱安全机制 :
 *      避免了代码被污染
 *
 * JNI = java Native interface
 * java7 以前的说法，方法区UserService => 永久代UserServiceImpl
 */
public class ClassLoader {

    public static void main(String[] args) {

        Object obj = new Object();  // Java 自带的 rt.jar  bootstrap加载器加载
        ClassLoader cl = new ClassLoader();

        System.out.println(obj.getClass().getClassLoader());  // Object类的类加载器
        // null

        System.out.println("-------------");

        System.out.println(cl.getClass().getClassLoader());
        System.out.println(cl.getClass().getClassLoader().getParent());
        System.out.println(cl.getClass().getClassLoader().getParent().getParent());
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        // sun.misc.Launcher$ExtClassLoader@74a14482
        // null

    }
}
