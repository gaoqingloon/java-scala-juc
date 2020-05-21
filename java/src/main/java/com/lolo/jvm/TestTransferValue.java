package com.lolo.jvm;

/**
 * 方法的作用域：传值、传引用
 */
public class TestTransferValue {

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("xxx");
    }

    public void changeValue3(String str) {  // 此处的str为临时变量
        str = "xxx";
    }

    public static void main(String[] args) {

        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age: " + age);

        Person person = new Person("abc", 18);
        test.changeValue2(person);
        System.out.println("person name: " + person.getName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("string: " + str);
    }
}
/*
age: 20
person name: xxx
string: abc
 */