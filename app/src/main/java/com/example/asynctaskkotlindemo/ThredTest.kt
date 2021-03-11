package com.example.asynctaskkotlindemo

import kotlin.concurrent.thread

fun main() {
    Thread(MyThread()).start()

    Thread {
        println("Hello多线程")
    }.start()

    thread { println("Hello多线程") }
}

class MyThread : Runnable {
    override fun run() {
        print("Hello多线程")
    }
}