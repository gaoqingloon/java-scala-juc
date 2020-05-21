package com.itedu.lesson_06.proxy.staticproxy;

import com.itedu.lesson_06.proxy.BuyHouse;

public class StaticProxyTest {

    public static void main(String[] args) {

        BuyHouse person = new BuyHouse();
        StaticProxy proxy = new StaticProxy(person);
        proxy.sell();
    }
}
