import edu.princeton.cs.algs4.StdDraw
import kotlin.math.pow
import kotlin.math.sqrt

class Point2D(var x: Double, var y: Double) : Comparable<Point2D> {
	fun distanceSquaredTo(other: Point2D): Double {
		val xLen = this.x - other.x
		val yLen = this.y - other.y
		return sqrt(xLen.pow(2) + yLen.pow(2))
	}

	fun draw() {
		StdDraw.point(x, y)
	}

	override fun equals(other: Any?) = (other is Point2D) &&
					x == other.x &&
					y == other.y

	override fun toString(): String {
		return "{%f, %f}".format(x, y)
	}

	override fun compareTo(other: Point2D): Int {
		return distanceSquaredTo(other).toInt()
	}

	override fun hashCode(): Int {
		var result = x.hashCode()
		result = 31 * result + y.hashCode()
		return result
	}
}