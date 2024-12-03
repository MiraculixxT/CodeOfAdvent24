package de.miraculixx.aoc24

import java.io.File

fun readInputFile(day: Int, task: String): String {
    val dayDigit = day.toString().padStart(2, '0')
    val file = File("input/day$dayDigit/$task.txt")
    try {
        return file.readText()
    } catch (e: Exception) {
        throw RuntimeException("Could not read file $file", e)
    }
}

