package com.itedu.lesson_06.factorymethod;

/**
 * 奔驰工厂
 */
public class BenchiFactory {

    public static Car createCar(String benchiName) {
        Car car;
        if (benchiName.equals("A1")) {
            car = new BenChi();
        }
        else {
            car = new BenChi();
        }
        return car;
    }
}
