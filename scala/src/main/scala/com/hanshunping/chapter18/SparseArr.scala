package com.hanshunping.chapter18

import scala.collection.mutable.ArrayBuffer

object SparseArr {

    def main(args: Array[String]): Unit = {

        // 演示一个稀疏数组的使用
        val rowSize = 11
        val colSize = 11
        val chessMap = Array.ofDim[Int](rowSize, colSize)

        // 初始化地图
        chessMap(1)(2) = 1  // 1 表示黑子
        chessMap(2)(3) = 2  // 2 表示黑子

        // 输出原始的地图
        for (item <- chessMap) {
            for (elem <- item) {
                printf("%d\t", elem)
            }
            println()
        }

        // 将chessMap转成稀疏数组
        // 思路 =》 效果是达到对数据的压缩
        // class Node(row, col, value)
        // ArrayBuffer
        val sparseArr = ArrayBuffer[Node]()
        val newNode = new Node(rowSize, colSize, 0)
        sparseArr.append(newNode)
        for (i <- 0 until chessMap.length) {
            for (j <- 0 until chessMap(i).length) {
                // 判断该值是否为0，不为0保存
                if (chessMap(i)(j) != 0) {
                    // 构建一个Node
                    val node = new Node(i, j, chessMap(i)(j))
                    sparseArr.append(node)  // 加入到稀疏数组
                }
            }
        }

        println("------稀疏数组------")

        // 输出稀疏数组
        for (node <- sparseArr) {
            printf("%d\t%d\t%d\n", node.row, node.col, node.value)
        }

        // 存盘

        // 读盘 -> 稀疏数组

        // 稀疏数组 -> 原始数组

        // 读取稀疏数组的第一个节点
        val newNode2 = sparseArr(0)
        val newRowSize = newNode2.row
        val newColSize = newNode2.col
        val chessMap2 = Array.ofDim[Int](newRowSize, newColSize)

        for (i <- 1 until sparseArr.length) {
            val node = sparseArr(i)
            chessMap2(node.row)(node.col) = node.value
        }

        println("----从稀疏数组恢复后的地图----")

        for (item <- chessMap2) {
            for (elem <- item) {
                printf("%d\t", elem)
            }
            println()
        }
    }
}


class Node(val row: Int, val col: Int, val value: Int)
