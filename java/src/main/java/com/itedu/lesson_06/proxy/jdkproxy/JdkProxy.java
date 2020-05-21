package com.itedu.lesson_06.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk 动态代理类
 *      实现 InvocationHandler 接口
 */
public class JdkProxy implements InvocationHandler {

    public Object target;
    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是房产中介，使用jdk动态代理类开始监听了");
        Object invoke = method.invoke(target, args);
        System.out.println("我是房产中介，使用jdk动态代理类结束监听了");
        return invoke;
    }
}
