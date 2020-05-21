package com.itedu.lesson_06.simplefactory;

public class Main {

    public static void main(String[] args) {

//        AoDi aoDi = new AoDi();
//        BenChi benChi = new BenChi();
//        aoDi.run();
//        benChi.run();


        Car car1 = CarFactory.createCar("奥迪");
        Car car2 = CarFactory.createCar("奔驰");
        car1.run();
        car2.run();
    }
}
