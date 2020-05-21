package com.itedu.lesson_06.proxy.cglibproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 需要两个jar包：
 *      asm: cglib的依赖包
 *      cglib
 *
 * 实现 MethodInterceptor 接口
 */
public class CglibProxy implements MethodInterceptor {

    /**
     *
     * @param obj：对象
     * @param method：方法
     * @param args：参数
     * @param methodProxy：代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("我是房产中介，使用cglib动态代理类开始监听了");
        Object invokeSuper = methodProxy.invokeSuper(obj, args);
        System.out.println("我是房产中介，使用cglib动态代理类结束监听了");
        return invokeSuper;
    }
}
