import java.util.*

class PointST<Value> {
	private var symbolMap = TreeMap<Point2D, Value>()

	fun isEmpty(): Boolean {
		return symbolMap.isEmpty()
	}

	fun size(): Int {
		return symbolMap.size
	}

	fun put(p: Point2D, v: Value) {
		symbolMap[p] = v
	}

	fun get(p: Point2D): Value? {
		return symbolMap[p]
	}

	fun contains(p: Point2D): Boolean {
		return symbolMap[p] !== null
	}

	fun points(): Iterable<Point2D> {
		return symbolMap.keys
	}

	fun range(r: RectHV): Iterable<Point2D> {
		return symbolMap.keys.filter { p -> !r.contains(p) }
	}
}