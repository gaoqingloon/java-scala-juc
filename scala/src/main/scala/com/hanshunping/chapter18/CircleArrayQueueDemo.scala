package com.hanshunping.chapter18

import scala.io.StdIn


object CircleArrayQueueDemo {

    def main(args: Array[String]): Unit = {
        // 初始化一个队列
        val queue = new CircleArrayQueue(4)

        var key = ""
        while (true) {

            println("show: 表示显示队列")
            println("exit: 表示退出程序")
            println("add: 表示添加数据")
            println("get: 表示取出数据")
            println("head: 表示查看队列头数据")

            key = StdIn.readLine()
            key match {
                case "show" => queue.showQueue()
                case "add" => {
                    println("请输入一个数")
                    val num = StdIn.readInt()
                    queue.addQueue(num)
                }
                case "get" => {
                    val res = queue.getQueue()
                    if (res.isInstanceOf[Exception]) {
                        println(res.asInstanceOf[Exception].getMessage)
                    }
                    else {
                        println(s"取出数据是: $res")
                    }
                }
                case "head" => {
                    val res = queue.headQueue()
                    if (res.isInstanceOf[Exception]) {
                        println(res.asInstanceOf[Exception].getMessage)
                    }
                    else {
                        println(s"头数据是: $res")
                    }
                }
                case "exit" => System.exit(0)
            }
        }
    }
}

// 环形队列和前面的单向队列有类似的地方，所以修改即可
class CircleArrayQueue(arrMaxSize: Int) {

    val maxSize = arrMaxSize
    val arr = new Array[Int](maxSize)  // 该数组存放数据，模拟队列
    var front = 0  // 指向队列的头部
    var rear = 0   // 指向队列的尾部

    // 判断队列满
    // 队列容量空出一个作为约定
    def isFull(): Boolean = {
        (rear + 1) % maxSize == front
    }

    // 判断队列空
    def isEmpty(): Boolean = {
        rear == front
    }

    // 添加数据
    def addQueue(ele: Int): Unit = {
        // 判断是否满
        if (isFull()) {
            println("队列满，无法加入...")
            return
        }
        arr(rear) = ele
        rear += (rear + 1) % maxSize  // rear后移，需要取模
    }

    // 从队列取数据
    def getQueue(): Any = {
        if (isEmpty()) {
            return new Exception("队列空")
        }
        val value = arr(front)
        front = (front + 1) % maxSize
        value
    }

    // 显示队列的所有数据
    def showQueue(): Unit = {
        if (isEmpty()) {
            println("队列是空的，没有数据...")
            return
        }
        // 从front取，取几个
        // 动脑筋
        for (i <- front until front + size()) {
            printf("arr[%d]=%d\n", i % maxSize, arr(i % maxSize))
        }
    }

    // 返回当前环形队列元素个数
    // 动脑筋 【问题/需求 ---> 设计算法】
    def size(): Int = {
        // rear = 1
        // front = 0
        // maxSize = 3
        (rear + maxSize - front) % maxSize
    }

    // 查看队列的头元素，但是不是改变队列
    def headQueue(): Any = {
        if (isEmpty()) {
            return new Exception("队列为空")
        }
        return arr(front)
    }
}
