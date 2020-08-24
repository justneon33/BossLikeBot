package utils

import java.util.*

fun debug (message: String) {
    println("[" + Date().toString() + "] Debug: " + message)
}

fun info (message: String) {
    println("[" + Date().toString() + "] Info: " + message)
}

fun input (inputMessage: String) = println("$inputMessage -> ")