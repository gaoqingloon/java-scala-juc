package com.itedu.lesson_06.proxy;

public class BuyHouse implements House {

    @Override
    public void sell() {
        System.out.println("我要买房了!!!!");
    }
}
