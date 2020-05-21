package com.itedu.lesson_06.factorymethod;

/**
 * 奥迪工厂
 */
public class AodiFactory {

    public static Car createCar(String aodiName) {
        Car car;
        if (aodiName.equals("A6")) {
            car = new AoDi();
        }
        else {
            car = new AoDi();
        }
        return car;
    }
}
