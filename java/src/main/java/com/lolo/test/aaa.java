package com.lolo.test;

class aaa{

    private int i = getJ();
    private int j = 10;

    private int getJ(){
        return j;
    }

    public static void main(String[] args) {
        System.out.println((new aaa()).i);
    }
}

