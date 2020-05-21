package com.itedu.lesson_06.simplefactory;

/**
 * 简单工厂：来创建实例
 *      一个工厂来创造所有
 */
public class CarFactory {

    public static Car createCar(String carType) {

        Car car = null;

//        // jdk1.7 支持String
//        switch (carType) {
//            case "奥迪":
//                car = new AoDi();
//                break;
//            case "奔驰":
//                car = new BenChi();
//                break;
//            default:
//                break;
//        }

        if (carType.equals("奥迪")) {
            car = new AoDi();
        }
        else if (carType.equals("奔驰")) {
            car = new BenChi();
        }

        return car;
    }
}
