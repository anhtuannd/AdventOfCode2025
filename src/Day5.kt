import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Day5().process2()
}

class Day5 {
    fun process1() {
        val rangeList = mutableListOf<Pair<Long, Long>>()
        val itemList = mutableListOf<Long>()
        var isItem = false
        var count = 0
        File("input5.txt").forEachLine {
            if (it.trim() == "") {
                isItem = true
            } else {
                if (isItem) {
                    itemList.add(it.toLong())
                } else {
                    rangeList.add(
                        Pair(
                            it.substringBefore("-").toLong(),
                            it.substringAfter("-").toLong()
                        )
                    )
                }
            }
        }
        for (item in itemList) {
            for (range in rangeList) {
                if (item in range.first..range.second) {
                    count++
                    break
                }
            }
        }
        println(count)
    }

    fun process2() {
        val rangeList = mutableListOf<Pair<Long, Long>>()
        var isItem = false
        var count = 0L
        File("input5.txt").forEachLine {
            if (it.trim() == "") {
                isItem = true
            } else {
                if (!isItem) {
                    rangeList.add(
                        Pair(
                            it.substringBefore("-").toLong(),
                            it.substringAfter("-").toLong()
                        )
                    )
                }
            }
        }
        rangeList.sortBy { it.first }

        var index = 0
        while (index < rangeList.size) {
            val start = rangeList[index].first
            var end = rangeList[index].second
            index++
            while (index < rangeList.size && rangeList[index].first <= end) {
                if (rangeList[index].second > end) {
                    end = rangeList[index].second
                }
                index++
            }
            count += end - start + 1
        }

        println(count)
    }
}

