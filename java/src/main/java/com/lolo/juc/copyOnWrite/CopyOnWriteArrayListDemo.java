package com.lolo.juc.copyOnWrite;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，
 而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[] newElements，
 然后新的容器Object[] newElements里添加元素，添加完元素之后，
 再将原容器的引用指向新的容器 setArray(newElements);。
 这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器

    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

 Collection	Collections
 写时复制
 Collection 接口
 Collections 工具类	Collections.synchronizedList(list)
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
/*
[a7dcca, 822379, b4ba34]
[a7dcca, 822379, b4ba34, 19676f, 01105c, 944f92]
[a7dcca, 822379, b4ba34, 19676f, 01105c]
[a7dcca, 822379, b4ba34, 19676f]
[a7dcca, 822379, b4ba34, 19676f, 01105c, 944f92, baa2fe]
[a7dcca, 822379, b4ba34]
[a7dcca, 822379, b4ba34]
[a7dcca, 822379, b4ba34, 19676f, 01105c, 944f92, baa2fe, cbfaed]
[a7dcca, 822379, b4ba34, 19676f, 01105c, 944f92, baa2fe, cbfaed, ed9286]
[a7dcca, 822379, b4ba34, 19676f, 01105c, 944f92, baa2fe, cbfaed, ed9286, 5f71ae]
 */