import java.io.File
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Day2().process()
}

class Day2 {
    fun process() {
        var count = 0L
        File("input2.txt").readText().split(",").forEach {
            count += parseItem(it)
        }
        println(count)
    }

    fun parseItem(item: String): Long {
        val start = item.substringBefore("-").toLong()
        val end = item.substringAfter("-").toLong()
        var count = 0L
        for (number in start..end) {
            if (isErrorV2(number)) count += number
        }
        return count
    }

    fun isErrorV1(number: Long): Boolean {
        val numberOfDigit = log10(abs(number.toDouble())).toLong() + 1
        if (numberOfDigit % 2L == 1L) return false
        val divider = 10.0.pow((numberOfDigit / 2L).toDouble()).toLong()
        return number / divider == number % divider
    }

    fun isErrorV2(number: Long): Boolean {
        val numberOfDigit = log10(abs(number.toDouble())).toLong() + 1
        if (numberOfDigit < 2L) return false
        val halfLength = numberOfDigit / 2L
        for (repeatLength in 1L..halfLength) {
            if (numberOfDigit % repeatLength == 0L) {
                val divider = 10.0.pow(repeatLength.toDouble()).toLong()
                var remainder = number
                val modulus = number % divider
                while (remainder % divider == modulus) {
                    remainder /= divider
                }
                if (remainder == 0L) {
                    return true
                }
            }
        }
        return false
    }
}

