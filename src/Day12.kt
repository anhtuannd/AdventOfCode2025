import java.io.BufferedReader
import java.io.File
import java.io.Reader
import kotlin.math.abs
import kotlin.system.measureTimeMillis

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val runningTime = measureTimeMillis {
        Day9().process2()
    }
    println("Exec time: $runningTime")
}

class Day12 {
    val shapeList = mutableListOf<Shape>()
    val input = mutableListOf<Case>()
    var max = 0L

    fun process1() {
        max = 0L
        readInput()
        input.forEach { case ->
            for (i in 1 until case.size.first - 1) {
                for (j in 1 until case.size.second - 1) {

                }
            }
        }
        println(max)
    }

    fun process2() {
        max = 0L
        readInput()
        println(max)
    }

    fun loop() {
        for (k in 0 until 6) {
            for (r in 0 until 4) {
                for (f in 0 until 2) {

                }
            }
        }
    }

    fun tryShape(index: Int, rotate: Int, flip: Boolean, matrix: Array<BooleanArray>) {

    }

    fun readInput() {
        File("input12.txt").bufferedReader().use { reader ->
            // read 6 shapes
            repeat(6) { shapeList.add(readShape(reader)) }
            var line = reader.readLine()
            while (line != null) {
                input.add(
                    Case(
                        line.substringBefore(": ").readSize(),
                        line.substringAfter(": ").readSet()
                    )
                )
                line = reader.readLine()
            }
        }
    }

    fun readShape(reader: BufferedReader): Shape {
        val shape = Shape()
        reader.readLine()
        repeat(3) { row ->
            val line = reader.readLine().trim()
            repeat(3) { column ->
                shape[row][column] = line[column] == '#'
            }
        }
        reader.readLine()
        return shape
    }

    fun String.readSize(): Pair<Int, Int> = Pair(substringBefore('x').toInt(), substringAfter('x').toInt())
    fun String.readSet(): IntArray = this.split(' ').map { it -> it.toInt() }.toIntArray()

    class Case(val size: Pair<Int, Int>, val shapePool: IntArray)

    class Shape {
        val shape = Array(3) { BooleanArray(3) }
        operator fun get(row: Int): BooleanArray = shape[row]
        operator fun set(row: Int, value: BooleanArray) {
            shape[row] = value
        }
    }

    fun Shape.rotate(degrees: Int): Array<BooleanArray> {
        val n = 3
        // Helper to create a deep copy of the matrix
        val result = Array(n) { i -> this[i].copyOf() }

        fun rotateOnce() {
            val temp = Array(n) { i -> result[i].copyOf() }
            for (i in 0 until n) {
                for (j in 0 until n) {
                    result[j][n - 1 - i] = temp[i][j]
                }
            }
        }

        when (degrees) {
            1 -> rotateOnce()
            2 -> { rotateOnce(); rotateOnce() }
            3 -> { rotateOnce(); rotateOnce(); rotateOnce() }
            else -> {}
        }

        return result
    }

    fun Shape.flip(): Array<BooleanArray> {
        val temp = Array(3) { i -> this[i].copyOf() }
        for (i in 0 until 3) {
            temp[i][2] = this[i][0]
        }
        return temp
    }
}

