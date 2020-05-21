package com.lolo.javaUtral.jvm.oom;

/**
 * 调用intern的时候，会检查字符串池中是否含有该字符串。
 */
public class InternTest {
    public static void main(String[] args) {
        String Str1 = new String("www.runoob.com");
        String Str2 = new String("WWW.RUNOOB.COM");

        System.out.print("规范表示:" );
        System.out.println(Str1.intern());

        System.out.print("规范表示:" );
        System.out.println(Str2.intern());

        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = new String("ab");

        // 当str5调用intern的时候，会检查字符串池中是否含有该字符串。
        // 由于之前定义的str3已经进入字符串池中，所以会得到相同的引用。
        // 采用new 创建的字符串对象不进入字符串池
        System.out.println(str5.equals(str3));  // true
        System.out.println(str5 == str3);  // false
        System.out.println(str5.intern() == str3);  // true
        System.out.println(str5.intern() == str4);  // false
        /*
        规范表示:www.runoob.com
        规范表示:WWW.RUNOOB.COM
        true
        false
        true
        false
         */
    }
}
