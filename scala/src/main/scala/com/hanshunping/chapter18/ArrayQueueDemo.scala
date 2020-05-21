package com.hanshunping.chapter18

import scala.io.StdIn

/**
  * 数据空间的复用
  * 将数据通过取模的方式，将该数组当做一个环形队列处理
  *     满: (rear+1)%maxSize ==
  */
object ArrayQueueDemo {

    def main(args: Array[String]): Unit = {

        // 初始化一个队列
        val queue = new ArrayQueue(3)

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

/**
  * 使用数组模拟队列
  * @param arrMaxSize
  */
class ArrayQueue(arrMaxSize: Int) {

    val maxSize = arrMaxSize
    val arr = new Array[Int](maxSize)  // 该数组存放数据，模拟队列
    var front = -1  // 指向队列的头部，front指向队列数据的前一个位置
    var rear = -1  // 指向队列的尾部，rear指向队列的最后的数据

    // 判断队列是否满
    def isFull(): Boolean = {
        rear == maxSize - 1
    }

    // 判断队列是否空
    def isEmpty(): Boolean = {
        front == rear
    }

    // 添加队列
    def addQueue(ele: Int): Unit = {
        // 判断是否满
        if (isFull()) {
            println("队列满，无法加入...")
            return
        }
        rear += 1  // 先让rear 后移
        arr(rear) = ele
    }

    // 从队列取数据
    def getQueue(): Any = {
        if (isEmpty()) {
            return new Exception("队列空")
        }
        front += 1
        return arr(front)
    }

    // 显示队列的所有数据
    def showQueue(): Unit = {
        if (isEmpty()) {
            println("队列是空的，没有数据...")
            return
        }
        for (i <- front+1 to rear) {
            printf("arr[%d]=%d\n", i, arr(i))
        }
    }

    // 查看队列的头元素，但是不是改变队列
    def headQueue(): Any = {
        if (isEmpty()) {
            return new Exception("队列为空")
        }
        return arr(front + 1)
    }
}
