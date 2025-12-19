
public inline fun <T> Iterable<T>.existsBoth(predicate1: (T) -> Boolean, predicate2: (T) -> Boolean): Boolean {
    if (this is Collection && isEmpty()) return false
    var found1 = false
    var found2 = false
    for (element in this) {
        if (predicate1(element)) found1 = true
        if (predicate2(element)) found2 = true

    }
    return found1 && found2
}