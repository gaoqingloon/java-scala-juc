package com.itedu.lesson_06.proxy.cglibproxy;

import com.itedu.lesson_06.proxy.BuyHouse;
import com.itedu.lesson_06.proxy.House;
import net.sf.cglib.proxy.Enhancer;

public class CglibProxyTest {

    public static void main(String[] args) {

        CglibProxy cglibProxy = new CglibProxy();

        // 动态代理使用asm框架生成代理
        // 通过字节码生成动态代理类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BuyHouse.class);
        enhancer.setCallback(cglibProxy);

        House house = (House) enhancer.create();
        house.sell();
    }
}
