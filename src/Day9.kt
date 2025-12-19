import java.io.File
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

class Day9 {
    val pointList = mutableListOf<Point>()
    var max = 0L

    fun process1() {
        max = 0L
        File("input9.txt").forEachLine {
            pointList.add(
                Point(
                    it.substringBefore(",").toLong(),
                    it.substringAfter(",").toLong()
                )
            )
        }
        for (start in 0..<pointList.size - 1) {
            for (end in start + 1..<pointList.size) {
                assignMaxIfNeed(pointList[start], pointList[end])
            }
        }
        println(max)
    }

    fun process2() {
        max = 0L
        File("input9.txt").forEachLine {
            pointList.add(
                Point(
                    it.substringBefore(",").toLong(),
                    it.substringAfter(",").toLong()
                )
            )
        }

        // find all lines
        val lines = pointList.mapIndexed { index, point ->
            Line(point, pointList[(index+1)%pointList.size])
        }

        for (i in 0..<pointList.size - 1) {
            for (j in i + 1..<pointList.size) {

                val start = pointList[i]
                val end = pointList[j]
                if (!getRectangle(start, end).isIntersectWithAny(lines)) {
                    assignMaxIfNeed(start, end)
                }
            }
        }

        println(max)
    }

    fun assignMaxIfNeed(start: Point, end: Point) {
        val square = square(start, end)
        if (square > max) {
            max = square
        }
    }

    fun square(start: Point, end: Point) = (abs(start.x - end.x) + 1) * (abs(start.y - end.y) + 1)
}

