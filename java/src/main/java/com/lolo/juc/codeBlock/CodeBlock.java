package com.lolo.juc.codeBlock;


class Code {

    public Code() {
        System.out.println("Code的构造方法1");
    }

    {
        System.out.println("Code的构造块2");
    }

    static {
        System.out.println("Code的静态代码块3");
    }
}


public class CodeBlock {  /// CodeBlock.class -- staticBlock

    {
        System.out.println("CodeBlock的构造块4");
    }

    static {
        System.out.println("CodeBlock的静态代码块5");
    }

    public CodeBlock() {
        System.out.println("CodeBlock的构造方法6");
    }

    public static void main(String[] args) {

        System.out.println("------ ^_^ ------");
        new Code();
        new CodeBlock();
    }
}

/*
CodeBlock的静态代码块5
------ ^_^ ------
Code的静态代码块3
Code的构造块2
Code的构造方法1
CodeBlock的构造块4
CodeBlock的构造方法6
 */