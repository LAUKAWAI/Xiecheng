package com.example.asynctaskkotlindemo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        val sum = withContext(Dispatchers.IO) {
            5 + 5
        }
        println(sum)
    }
}
