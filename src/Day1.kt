import java.io.File
import kotlin.math.abs

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Day1().process()
}

class Day1 {
    fun process() {
        var current = 50
        var count = 0
        File("input.txt").forEachLine {
            if (it.trim() != "") {
                val (next, count_zero) = parseLine(current, it.trim())
                current = next
                count += count_zero
            }
        }
        println(count)
    }

    fun parseLine(current: Int, line: String): Pair<Int, Int> {
        val isLeft = line.startsWith("L")
        val number = Integer.parseInt(line.substring(1))
        return count_pass_zero(current, isLeft, number)
    }

    fun rotate(current: Int, isLeft: Boolean, number: Int): Int {
        val add = if (isLeft) -number else number
        return ((current + add) % 100 + 100) % 100
    }

    fun count_pass_zero(current: Int, isLeft: Boolean, number: Int): Pair<Int, Int> {
        val add = if (isLeft) -number else number
        val next_pos = current + add
        val next = ((current + add) % 100 + 100) % 100
        val count = if (next_pos <= 0 && current > 0) abs(next_pos) / 100 + 1 else abs(next_pos) / 100
        return Pair(next, count)
    }
}

