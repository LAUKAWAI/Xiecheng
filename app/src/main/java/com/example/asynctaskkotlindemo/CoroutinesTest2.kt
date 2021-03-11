package com.example.asynctaskkotlindemo

import kotlinx.coroutines.*

fun main() {
    val job = Job()
    val scope = CoroutineScope(job)
    scope.launch {
        for (i in 5 downTo 1) {
            println(i)
            delay(1000)
        }
    }
    job.cancel()
}

//suspend fun printDot() = coroutineScope {
//    launch {
//        delay(1000)
//        println("挂起函数")
//    }
//}