package com.itedu.lesson_06.factorymethod;

public class Main {

    public static void main(String[] args) {

        Car a6 = AodiFactory.createCar("A6");
        Car a2 = BenchiFactory.createCar("A2");
        a6.run();
        a2.run();
    }
}
