package com.lolo.javaUtral.thread;

import com.lolo.javaUtral.entities.Person;

public class TestTransferValue {

    public void changeValue1(int age) {
        age = 30;
    }
    public void changeValue2(Person person) {
        person.setPersonName("xxx");
    }
    public void changeValue3(String str) {
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age----" + age);  //20

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName: " + person.getPersonName());  //xxx

        String str = "abc";  // 字符串常量池 字符串不可以修改只能创建
        test.changeValue3(str);
        System.out.println("String: " + str);  //abc

        String str1 = new String("abc");
        test.changeValue3(str1);
        System.out.println("String: " + str1);  //abc
    }
}
