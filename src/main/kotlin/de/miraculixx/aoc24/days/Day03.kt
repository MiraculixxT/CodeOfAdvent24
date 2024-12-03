package de.miraculixx.aoc24.days

import de.miraculixx.aoc24.readInputFile

fun main() {
    val day02 = Day02(readInputFile(3, "b"))
    println("Day 01 - Part 1")
    day02.part1()

    println("\nDay 01 - Part 2")
    day02.part2()
}

class Day02(private val input: String) {

    private var last: Char? = null
    private var num: String = ""
    private var m1 = -1
    private fun searchMultiplier(newInput: String): Int {
        var totalMul = 0
        newInput.forEachIndexed { index, c ->
            when (last) {
                null -> if (c == 'm') last = c else reset(index) // n
                'm' -> if (c == 'u') last = c else reset(index)  // nu
                'u' -> if (c == 'l') last = c else reset(index)  // num
                'l' -> if (c == '(') last = c else reset(index)  // num(
                '(' -> {
                    when {
                        c.isDigit() -> if (num.length < 3) num += c else reset(index) // num(123
                        c == ',' -> {
                            when {
                                num.isEmpty() -> reset(index) // num(,
                                m1 == -1 -> { // num(123,
                                    m1 = num.toInt()
                                    num = ""
                                }
                                else -> reset(index) // num(123,456,
                            }
                        }
                        c == ')' -> {
                            if (m1 != -1 && num.isNotEmpty()) {
                                totalMul += m1 * num.toInt()
                                println("Mul: $m1 * $num = ${m1 * num.toInt()} ($totalMul)")
                            } // num(123,456)
                            reset(index)
                        }
                        else -> reset(index)
                    }
                }
            }
        }
        return totalMul
    }

    private fun reset(index: Int) {
        println("Reset at $last ($index -> ${input[index]})")
        last = null
        num = ""
        m1 = -1
    }

    fun part1() {
        val mul = searchMultiplier(input)
        println("Total mul: $mul")
    }

    fun part2() {
        val newInput = buildString {
            input.split("don't()").forEachIndexed { index, s ->
                if (index == 0) {
                    append(s)
                    return@forEachIndexed
                }
                val allowedSplit = s.split("do()")
                allowedSplit.subList(1, allowedSplit.size).forEach { append(it) }
            }
        }
        println(newInput)
        val mul = searchMultiplier(newInput)
        println("Total mul: $mul")
    }
}