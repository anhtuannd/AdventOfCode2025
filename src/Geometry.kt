import kotlin.math.sqrt

data class Point(val x: Long, val y: Long) {
    override fun toString(): String {
        return "($x-$y)"
    }
}

data class Line(val start: Point, val end: Point) {
    override fun toString(): String {
        return "[$start -> $end]"
    }

    fun isHorizontal(): Boolean = start.y == end.y
}

data class Rectangle(val left: Long, val top: Long, val right: Long, val bottom: Long)

fun Line.toRectangle(): Rectangle {
    val (left, right) = minAndMax(start.x, end.x)
    val (top, bottom) = minAndMax(start.y, end.y)
    return Rectangle(left, top, right, bottom)
}

fun getRectangle(start: Point, end: Point): Rectangle {
    val (left, right) = minAndMax(start.x, end.x)
    val (top, bottom) = minAndMax(start.y, end.y)
    return Rectangle(left, top, right, bottom)
}

fun Long.square() = this * this

fun Point.distanceTo(another: Point): Double =
    sqrt((another.x - x).square().toDouble() + (another.y - y).square().toDouble())

fun Line.length() = start.distanceTo(end)

fun minAndMax(a: Long, b: Long): Pair<Long, Long> = if (a > b) Pair(b,a) else Pair(a, b)

fun Rectangle.isIntersectWithAny(lines: List<Line>): Boolean = lines.any { line -> isIntersectWith(line) }

fun Rectangle.isIntersectWith(line: Line): Boolean {
    if (line.isHorizontal()) {
        val (lineLeft, lineRight) = minAndMax(line.start.x, line.end.x)
        return line.start.y in top+1..<bottom && (lineRight > left) && (lineLeft < right)
    } else {
        val (lineTop, lineBottom) = minAndMax(line.start.y, line.end.y)
        return line.start.x in left+1..<right && (lineBottom > top) && (lineTop < bottom)
    }
}

