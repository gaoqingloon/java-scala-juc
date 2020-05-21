package com.lolo.jvm;

import java.util.Random;

public class OOM {

    public int add(int x, int y) {
        int result = 0;
        result = x + y;
        return result;
    }

    public static void test01() {
        Object o = new Object();
        test02();
    }

    private static void test02() {
        test02();
    }

    public static void testMemory() {

        // 返回 Java 虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("MAX_MEMORY = " + maxMemory + " bytes = " +
                (maxMemory / (double) 1024 / 1024) + " MB");

        // 返回 Java 虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("TOTAL_MEMORY = " + totalMemory + " bytes = " +
                (totalMemory / (double) 1024 / 1024) + " MB");
        /*
MAX_MEMORY = 3804758016 byte = 3628.5 MB
TOTAL_MEMORY = 257425408 byte = 245.5 MB
         */

        // -Xms 初始1G，-Xmx最大1G
        // VM参数：	-Xms1024m -Xmx1024m -XX:+PrintGCDetails
        /*
MAX_MEMORY = 1029177344 bytes = 981.5 MB
TOTAL_MEMORY = 1029177344 bytes = 981.5 MB
Heap
 PSYoungGen      total 305664K, used 20971K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
  eden space 262144K, 8% used [0x00000000eab00000,0x00000000ebf7afb8,0x00000000fab00000)
  from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
  to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
 ParOldGen       total 699392K, used 0K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
  object space 699392K, 0% used [0x00000000c0000000,0x00000000c0000000,0x00000000eab00000)
 Metaspace       used 3232K, capacity 4500K, committed 4864K, reserved 1056768K
  class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
         */
    }


    public static void main(String[] args) {

        /** 1.  */
//        test01();
        // Exception in thread "main" java.lang.StackOverflowError
        // 解决问题的网站: https://stackoverflow.com/


        /** 2.  */
//        testMemory();


        /** 3.  */
        // Java 虚拟机调优：要不别调，调的话推荐调到大小一样，防止内存的大起大落
        // VM参数：-Xms8m -Xmx8m -XX:+PrintGCDetails
        String str = "helloWorld";
        while (true) {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999);
        }
        /*
[GC (Allocation Failure) [PSYoungGen: 1536K->504K(2048K)] 1536K->640K(7680K), 0.0014697 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 1812K->491K(2048K)] 1948K->958K(7680K), 0.0158339 secs] [Times: user=0.05 sys=0.00, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 1852K->344K(2048K)] 3166K->1869K(7680K), 0.0077987 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 1264K->400K(2048K)] 6182K->5317K(7680K), 0.0009586 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) --[PSYoungGen: 1283K->1283K(2048K)] 6201K->6201K(7680K), 0.0187475 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 1283K->0K(2048K)] [ParOldGen: 4917K->2480K(5632K)] 6201K->2480K(7680K), [Metaspace: 3193K->3193K(1056768K)], 0.0032265 secs] [Times: user=0.02 sys=0.03, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 67K->128K(2048K)] 4243K->4303K(7680K), 0.0006844 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 128K->96K(2048K)] 4303K->4271K(7680K), 0.0002627 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Allocation Failure) [PSYoungGen: 96K->0K(2048K)] [ParOldGen: 4175K->3141K(5632K)] 4271K->3141K(7680K), [Metaspace: 3200K->3200K(1056768K)], 0.0077137 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] 3141K->3141K(7680K), 0.0016315 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] [ParOldGen: 3141K->3121K(5632K)] 3141K->3121K(7680K), [Metaspace: 3200K->3200K(1056768K)], 0.0075310 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3332)
	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:674)
	at java.lang.StringBuilder.append(StringBuilder.java:208)
	at com.lolo.jvm.OOM.main(OOM.java:72)
Heap
 PSYoungGen      total 2048K, used 84K [0x00000000ffd80000, 0x0000000100000000, 0x0000000100000000)
  eden space 1536K, 5% used [0x00000000ffd80000,0x00000000ffd95360,0x00000000fff00000)
  from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
  to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
 ParOldGen       total 5632K, used 3121K [0x00000000ff800000, 0x00000000ffd80000, 0x00000000ffd80000)
  object space 5632K, 55% used [0x00000000ff800000,0x00000000ffb0c628,0x00000000ffd80000)
 Metaspace       used 3250K, capacity 4500K, committed 4864K, reserved 1056768K
  class space    used 352K, capacity 388K, committed 512K, reserved 1048576K
         */
    }
}
