package com.lolo.juc.lambda;

interface Foo {
    public void sayHello();
}

interface Foo2 {
    void sayHello();
    void say886();
}

@FunctionalInterface
interface Foo3 {
    public int add(int x, int y);
}

interface Foo4 {

    // 若想被lambda表达式改写，接口内只允许有一个普通方法，
    // 可以有多个default方法和多个static方法
    int add(int x, int y);

    // java8 允许在接口中实现方法，需要添加default默认方法的实现
    default int mul(int x, int y) {
        return x * y;
    }

    default int sub(int x, int y) {
        return x - y;
    }

    static int div(int x, int y) {
        return x / y;
    }
}

/**
 * Lambda Express : 函数式编程
 *      1. 前提，接口里面有且仅有一个方法声明
 *      2. 拷贝中括号，写死右箭头，落地大括号【重要】
 *      3. @FunctionalInterface 标注，不写隐式定义了，不允许接口内多个方法
 *      4. default 方法实现(少用)
 *      5. static 方法实现(少用)
 */
public class LambdaDemo {

    public static void main(String[] args) {

        System.out.println("----------1-----------");
        Foo2 foo2 = new Foo2() {
            @Override
            public void sayHello() {
                System.out.println("hello world...");
            }

            @Override
            public void say886() {
                System.out.println("hello world 886...");
            }
        };
        foo2.sayHello();
        foo2.say886();

        System.out.println();
        System.out.println("----------2-----------");
        // Lambda Express
        Foo foo = () -> {
            System.out.println("lambda hello world...");
        };
        foo.sayHello();

        System.out.println();
        System.out.println("----------3-----------");
        Foo3 foo3 = (int x, int y) -> {
            System.out.println("lambda add x y...");
            return x + y;
        };
        System.out.println(foo3.add(3, 4));

        System.out.println();
        System.out.println("----------4-----------");
        Foo4 foo4 = (int x, int y) -> {
            System.out.println("lambda default add x y");
            return x + y;
        };
        System.out.println(foo4.mul(4, 2));
        System.out.println(foo4.add(4, 2));
        System.out.println(foo4.sub(4, 2));
        System.out.println(Foo4.div(4, 2));
    }
}
