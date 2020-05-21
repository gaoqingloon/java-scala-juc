package com.lolo.juc.codeBlock;


class Father {  /// Father.class -- Son.class -- FatherInstance -- SonInstance

    public Father() {
        System.out.println("--1--");
    }

    {
        System.out.println("--2--");
    }

    static {
        System.out.println("--3--");
    }
}


class Son extends Father {

    public Son() {
        //super();
        System.out.println("--4--");
    }

    {
        System.out.println("--5--");
    }

    static {
        System.out.println("--6--");
    }
}


public class StaticSeq {

    public static void main(String[] args) {

        new Son();  // 从父到子，静态先行(且静态只加载一次)
        System.out.println("-- ^_^ --");
        new Son();
        System.out.println("-- ^_^ --");
        new Father();
    }
}
/*
--3--
--6--
--2--
--1--
--5--
--4--
-- ^_^ --
--2--
--1--
--5--
--4--
-- ^_^ --
--2--
--1--
 */