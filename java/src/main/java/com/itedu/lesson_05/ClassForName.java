package com.itedu.lesson_05;

import com.itedu.lesson_05.entity.UserEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassForName {

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

//        // 1. 可以new创建对象，还可以使用反射机制创建对象
//        //UserEntity userEntity = new UserEntity();
//        // forName 必须传入class类的完整路径
//        Class<?> forName = Class.forName("com.itedu.lesson_05.entity.UserEntity");
//
//        // 2. newInstance使用无参构造函数 创建对象
//        Object newInstance = forName.newInstance();
//        UserEntity user = (UserEntity) newInstance;
//
//        System.out.println("user: " + user);
//        user.setUserId("123");
//        System.out.println(user.getUserId());
        /*
        使用反射技术，执行无参构造函数
        user: UserEntity(userId=null, userName=null)
        123
         */

        // 使用反射技术创建对象和new 哪个效率高？  new 效率高


        ////////////////////////////////

//        Class<?> forName = Class.forName("com.itedu.lesson_05.entity.UserEntity");
//        Constructor<?> constructor = forName.getConstructor(String.class);
//        Object newInstance = constructor.newInstance("111");
//        UserEntity user = (UserEntity) newInstance;
//        System.out.println("user: " + user);

        ////////////////////////////////

        Class<?> forName = Class.forName("com.itedu.lesson_05.entity.UserEntity");
        // 获取该类的所有方法
        Method[] declaredMethods = forName.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName() + "---" + method.getReturnType());
        }
        System.out.println("------------");

        // 获取该类的所有方法(包括继承)
        Method[] methods = forName.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + "===" + method.getReturnType());
        }
        System.out.println("------------");

        // 获取所有的成员属性
        Field[] fields = forName.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("------------");

        // 为什么继承里面没有
        // 可以使用java反射技术 可以访问到私有属性
        Field declaredField = forName.getDeclaredField("userId");
        UserEntity newInstance = (UserEntity) forName.newInstance();
        // 允许访问私有成员属性
        declaredField.setAccessible(true);
        declaredField.set(newInstance, "123");
        System.out.println(newInstance);
    }
}
