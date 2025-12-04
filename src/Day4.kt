import java.io.File
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Day4().process()
}

class Day4 {
    fun process() {
        var count = 0L
        File("input3.txt").forEachLine {
            count += parseItem(it)
        }
        println(count)
    }

    fun parseItem(item: String): Long {
        var total = 0L
        var currentStart = 0
        val maxDigit = 12
        for (startPos in 0..<maxDigit) {
            var first = 0
            val lastItemPosition = item.length - (maxDigit - startPos - 1)
            for (pos in currentStart..<lastItemPosition) {
                if (first < (item[pos] - '0')) {
                    first = item[pos] - '0'
                    currentStart = pos + 1
                }
            }
            total = total * 10 + first
        }

        return total
    }
}

