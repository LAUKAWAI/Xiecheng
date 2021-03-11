package com.example.asynctaskkotlindemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val start = System.currentTimeMillis();
    println("你好，安卓！")
    runBlocking {//创建顶层协程
        repeat(100000){
            launch {
                println("你好，协程！")
            }
        }
    }
    val end =System.currentTimeMillis()
    println("${end - start}毫秒")
}
