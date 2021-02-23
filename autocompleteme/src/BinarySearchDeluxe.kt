fun <Key> firstIndexOf(a: Array<Key>, key: Key, cmp: Comparator<Key>): Int {
    var low = 0
    var high = a.size
    var mid: Int
    while (low < high) {
        mid = low + ((high - low) / 2)
        val compareVal = cmp.compare(key, a[mid])
        when {
            compareVal < 0 -> high = mid + 1
            compareVal == 0 -> when {
                mid == 0 || cmp.compare(key, a[mid - 1]) != 0 -> return mid
                else -> high = mid + 1
            }
            compareVal > 0 -> low = mid - 1
        }
    }
    return -1
}

fun <Key> lastIndexOf(a: Array<Key>, key: Key, cmp: Comparator<Key>): Int {
    var low = 0
    var high = a.size
    var mid: Int
    while (low < high) {
        mid = low + ((high - low) / 2)
        val compareVal = cmp.compare(key, a[mid])
        when {
            compareVal < 0 -> high = mid + 1
            compareVal == 0 -> when {
                mid == a.size || cmp.compare(a[mid + 1], key) != 0 -> return mid
                else -> low = mid - 1
            }
            compareVal > 0 -> low = mid - 1
        }
    }
    return -1
}