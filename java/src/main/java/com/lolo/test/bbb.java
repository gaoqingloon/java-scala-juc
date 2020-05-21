package com.lolo.test;
////
//public class bbb extends aaa{
////    public void getNum(double d) {}
////    public void getNum() {}  //方法重写，没有返回值的不同
////    public float getNum() {return 4.0f;}  //over
//    public double getNum(float d) {return 4.0d;}
//}

public class bbb {
    public static void main(String[] args) {
        int c = 5/2;
        double d = 5/2;
        System.out.println(c);
        System.out.println(d);

        String a1 = "a" + "b";
        String a2 = new String(a1);
        if (a1 == a2) {
            System.out.println("==");
        }else if (a1.equals(a2)) {
            System.out.println("equals");
        }
    }
}
