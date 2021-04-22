import edu.princeton.cs.algs4.StdDraw
import kotlin.math.pow
import kotlin.math.sqrt

class RectHV(var xmin: Double, var ymin: Double, var xmax: Double, var ymax: Double) {
	fun contains(p: Point2D): Boolean {
		return p.x in xmin..xmax && p.y in ymin..ymax
	}

	fun intersects(other: RectHV): Boolean {
		return xmax >= other.xmin &&
						ymax >= other.ymin &&
						other.xmax >= xmin &&
						other.ymax >= ymin
	}

	fun distanceSquaredTo(p: Point2D): Double {
		var diffX = 0.0
		var diffY = 0.0
		when {
			p.x < xmin -> diffX = p.x - xmin
			p.x > xmax -> diffX = p.x - xmax
			p.y < ymin -> diffY = p.y - ymin
			p.y > ymax -> diffY = p.y - ymax
		}
		return sqrt(diffX.pow(2) + diffY.pow(2))
	}

	fun draw() {
		StdDraw.polygon(doubleArrayOf(xmin, xmax, xmin, xmax), doubleArrayOf(ymin, ymax, ymax, ymin))
	}

	override fun equals(other: Any?) = (other is RectHV) &&
					xmin == other.xmin &&
					xmax == other.xmax &&
					ymin == other.ymin &&
					ymax == other.ymax

	override fun hashCode(): Int {
		var result = xmin.hashCode()
		result = 31 * result + ymin.hashCode()
		result = 31 * result + xmax.hashCode()
		result = 31 * result + ymax.hashCode()
		return result
	}

	override fun toString(): String {
		return "{%f %f %f %f}".format(xmin, xmax, ymin, ymax)
	}
}