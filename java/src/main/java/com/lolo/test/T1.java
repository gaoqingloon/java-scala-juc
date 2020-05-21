package com.lolo.test;

class T11 {
//    private T11() {}
//    private static T11 t11;
//    public static T11 getT11() {
//        if (t11 == null) {
//            synchronized (T11.class) {
//                t11 = new T11();
//            }
//        }
//        return t11;
//    }

    private T11() {}
    private static final T11 t11 = new T11();
    public static T11 getT11() {
        return t11;
    }
}
public class T1 {
    public static void main(String[] args) {
        T11 t11 = T11.getT11();
        T11 t12 = T11.getT11();
        System.out.println(t11 == t12);
    }
}
