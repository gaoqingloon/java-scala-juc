package com.itedu.lesson_06.proxy.jdkproxy;

import com.itedu.lesson_06.proxy.BuyHouse;
import com.itedu.lesson_06.proxy.House;

import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) {

        BuyHouse person = new BuyHouse();
        JdkProxy jdkProxy = new JdkProxy(person);

        House house = (House) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                                                     person.getClass().getInterfaces(),
                                                     jdkProxy);
        house.sell();
    }
}
