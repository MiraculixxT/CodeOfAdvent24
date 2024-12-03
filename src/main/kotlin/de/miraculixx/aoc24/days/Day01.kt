package de.miraculixx.aoc24.days

import de.miraculixx.aoc24.readInputFile

fun main() {
    val day01 = Day01(readInputFile(1, "a"))
    println("Day 01 - Part 1")
    day01.part1()

    println("\nDay 01 - Part 2")
    day01.part2()
}

class Day01(private val input: String) {
    val listLeft = mutableListOf<Int>()
    val listRight = mutableListOf<Int>()

    init {
        calcLists()
    }

    private fun calcLists() {
        // Read all numbers
        input.lines().forEach { line ->
            val split = line.split("   ")
            val number1 = split[0].toInt()
            val number2 = split[1].toInt()
            listLeft.add(number1)
            listRight.add(number2)
        }
    }

    fun part1() {
        // Sort the lists
        listLeft.sort()
        listRight.sort()

        // Find the matching numbers
        var totalDiff = 0
        listLeft.forEachIndexed { index, number1 ->
            val number2 = listRight[index]
            val diff = if (number1 > number2) number1 - number2 else number2 - number1
            totalDiff += diff
        }

        println("Total diff: $totalDiff")
    }

    fun part2() {
        var totalSimilarityScore = 0
        listLeft.forEach { number ->
            val amount = listRight.count { it == number }
            totalSimilarityScore += number * amount
        }

        println("Total similarity score: $totalSimilarityScore")
    }
}