package com.lolo.javaUtral.jvm.oom;

/**
 * 递归程序撑爆栈空间
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();  // Exception in thread "main" java.lang.StackOverflowError
    }
}
