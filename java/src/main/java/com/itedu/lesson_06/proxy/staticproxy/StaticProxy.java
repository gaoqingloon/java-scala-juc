package com.itedu.lesson_06.proxy.staticproxy;

import com.itedu.lesson_06.proxy.BuyHouse;
import com.itedu.lesson_06.proxy.House;

/**
 * 静态代理类
 * 中介，一个接口一个实现
 */
public class StaticProxy implements House {

    private BuyHouse person;

    public StaticProxy(BuyHouse person) {
        this.person = person;
    }

    @Override
    public void sell() {
        System.out.println("我是中介，你买的操作，我开始监听啦!!!");
        person.sell();
        System.out.println("我是中介，你买的操作，我结束监听啦!!!");
    }
}
