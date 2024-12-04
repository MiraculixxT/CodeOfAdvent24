package de.miraculixx.aoc24.days

import de.miraculixx.aoc24.readInputFile

fun main() {
    val day = Day04(readInputFile(4, "b"))
    println("Day 01 - Part 1")
    day.part1()

    println("\nDay 01 - Part 2")
//    day02.part2()
}

class Day04(private val input: String) {
    private val xmasRegex = Regex("XMAS")
    private val xmasRegex2 = Regex("SAMX")
    private val lines = input.lines()

    fun part1() {
        var xmasCount = 0
        xmasCount += xmasRegex.findAll(input).count()
        xmasCount += xmasRegex2.findAll(input).count()
        println(xmasCount)
        lines.forEachIndexed { rowID, row ->
            row.forEachIndexed { colID, c ->
                if (c == 'X') xmasCount += searchDiagonal(rowID, colID, "XMAS")
                if (c == 'S') xmasCount += searchDiagonal(rowID, colID, "SAMX")
            }
        }
        println("XMAS count: $xmasCount")
    }

    private fun searchDiagonal(rowID: Int, colID: Int, charsLeft: String): Int {
        var count = 0
        if (searchDiagonal(rowID, colID, charsLeft, true, false)) count++
        if (searchDiagonal(rowID, colID, charsLeft, false, false)) count++
        if (searchDiagonal(rowID, colID, charsLeft, true, true)) count++
        return count
    }

    private fun searchDiagonal(nextRow: Int, nextCol: Int, charsLeft: String, right: Boolean, vertical: Boolean): Boolean {
        if (charsLeft.isEmpty()) return true
        if (nextRow !in lines.indices || nextCol !in lines[nextRow].indices) return false

        val char = lines[nextRow][nextCol]
        if (char != charsLeft[0]) return false
        if (vertical) return searchDiagonal(nextRow - 1, nextCol, charsLeft.drop(1), right, true)
        return searchDiagonal(
            nextRow - 1,
            nextCol + if (right) 1 else -1,
            charsLeft.drop(1), right, false
        )
    }

}