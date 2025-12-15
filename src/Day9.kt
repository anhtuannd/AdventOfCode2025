import java.io.File
import kotlin.math.abs

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Day9().process2()
}

class Day9 {
    val pointList = mutableListOf<Pair<Long, Long>>()
    var max = 0L

    fun process1() {
        max = 0L
        File("input9.txt").forEachLine {
            pointList.add(
                Pair(
                    it.substringBefore(",").toLong(),
                    it.substringAfter(",").toLong()
                )
            )
        }
        for (start in 0..<pointList.size - 1) {
            for (end in start + 1..<pointList.size) {
                assignMaxIfNeed(start, end)
            }
        }
        println(max)
    }

    fun process2() {
        max = 0L
        File("input9.txt").forEachLine {
            pointList.add(
                Pair(
                    it.substringBefore(",").toLong(),
                    it.substringAfter(",").toLong()
                )
            )
        }

        // find all horizontal line
        val horizontalLines = pointList
            .filterIndexed { index, _ -> index % 2 == 0 }
            .mapIndexed { index, pair ->
                Pair(pair, pointList[index*2 + 1])
            }

        val verticalLines = pointList
            .filterIndexed { index, _ -> index % 2 == 1 }
            .mapIndexed { index, pair ->
                if (index < pointList.size / 2 - 1) {
                    Pair(pair, pointList[(index + 1) * 2])
                } else {
                    Pair(pointList[0], pair)
                }
            }

        for (start in 0..<pointList.size - 1) {
            for (end in start + 1..<pointList.size) {

                if (isLineIntersect(start, end, horizontalLines, verticalLines)) {
                    assignMaxIfNeed(start, end)
                }
            }
        }

        println(max)
    }

    fun isLineIntersect(
        start: Int,
        end: Int,
        horizontalLines: List<Pair< Pair<Long, Long>,  Pair<Long, Long>>>,
        verticalLines: List<Pair< Pair<Long, Long>,  Pair<Long, Long>>>
    ): Boolean  {

        val p1 = pointList[start]
        val p2 = pointList[end]
        println("----- check $p1 $p2")

        val (left, right) = minAndMax(p1.first, p2.first)
        val (top, bottom) = minAndMax(p1.second, p2.second)

        return isVerticalLineIntersect(
            Pair(
                Pair(left, top),
                Pair(left, bottom)
            ),
            horizontalLines
        ) && isVerticalLineIntersect(
            Pair(
                Pair(right, top),
                Pair(right, bottom)
            ),
            horizontalLines
        )
                && isHorizontalLineIntersect(
            Pair(
                Pair(left, top),
                Pair(right, top)
            ),
            verticalLines
        ) && isHorizontalLineIntersect(
            Pair(
                Pair(left, bottom),
                Pair(right, bottom)
            ),
            verticalLines
        )
    }

    fun isVerticalLineIntersect(
        verticalLine: Pair< Pair<Long, Long>,  Pair<Long, Long>>,
        horizontalLines: List<Pair< Pair<Long, Long>,  Pair<Long, Long>>>
    ): Boolean  {
        return horizontalLines.any { horizontalLine ->
             verticalLine.first.first in horizontalLine.first.first+1.. horizontalLine.second.first
                    && horizontalLine.first.second in verticalLine.second.second..verticalLine.first.second
        }
    }

    fun isHorizontalLineIntersect(
        horizontalLine: Pair< Pair<Long, Long>,  Pair<Long, Long>>,
        verticalLines: List<Pair< Pair<Long, Long>,  Pair<Long, Long>>>
    ): Boolean  {
        return verticalLines.any { verticalLine ->
             horizontalLine.first.second in verticalLine.first.second+1..verticalLine.second.second
                    && verticalLine.first.first in horizontalLine.first.first..horizontalLine.second.first
        }
    }

    fun minAndMax(a: Long, b: Long): Pair<Long, Long> = if (a > b) Pair(b,a) else Pair(a, b)

    fun assignMaxIfNeed(start: Int, end: Int) {
        val square = square(start, end)
        if (square > max) {
            println("$square")
            max = square
        }
    }

    fun square(start: Int, end: Int) =
        (abs(pointList[start].first - pointList[end].first) + 1) *
                (abs(pointList[start].second - pointList[end].second) + 1)
}

