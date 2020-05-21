package com.lolo.juc.copyOnWrite;

import java.util.*;

/**
 * 集合类不安全
 */
public class NotSafeDemo {

    private static void notSafeList() {

//        List<String> list = new ArrayList<>();
//        list = Arrays.asList("a", "b", "c");
//        list.forEach(System.out::println);

        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        // java.util.ConcurrentModificationException
    }


    /**
        // Dummy value to associate with an Object in the backing Map
        private static final Object PRESENT = new Object();

        public HashSet() {
            map = new HashMap<>();
        }

        public boolean add(E e) {
            return map.put(e, PRESENT)==null;
        }
     */
    private static void notSafeSet() {

        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 50; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
        // java.util.ConcurrentModificationException
    }


    private static void notSafeMap() {

        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 50; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 6));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        // java.util.ConcurrentModificationException
    }


    public static void main(String[] args) {

        // notSafeList();
        // notSafeSet();
        notSafeMap();
    }
}

/*
java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
	at java.util.ArrayList$Itr.next(ArrayList.java:859)
	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
	at java.lang.String.valueOf(String.java:2994)
	at java.io.PrintStream.println(PrintStream.java:821)
	at com.lolo.juc.copyOnWriteArrayList.NotSafeDemo.lambda$main$0(NotSafeDemo.java:22)
	at java.lang.Thread.run(Thread.java:748)

----

Exception in thread "33" java.util.ConcurrentModificationException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1442)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1466)
	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
	at java.lang.String.valueOf(String.java:2994)
	at java.io.PrintStream.println(PrintStream.java:821)
	at com.lolo.juc.copyOnWriteArrayList.NotSafeDemo.lambda$notSafeSet$1(NotSafeDemo.java:44)
	at java.lang.Thread.run(Thread.java:748)

---

Exception in thread "34" java.util.ConcurrentModificationException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1442)
	at java.util.HashMap$EntryIterator.next(HashMap.java:1476)
	at java.util.HashMap$EntryIterator.next(HashMap.java:1474)
	at java.util.AbstractMap.toString(AbstractMap.java:554)
	at java.lang.String.valueOf(String.java:2994)
	at java.io.PrintStream.println(PrintStream.java:821)
	at com.lolo.juc.copyOnWrite.NotSafeDemo.lambda$notSafeMap$2(NotSafeDemo.java:58)
	at java.lang.Thread.run(Thread.java:748)
 */