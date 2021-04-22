import edu.princeton.cs.algs4.In
import edu.princeton.cs.algs4.StdDraw
import kotlin.jvm.JvmStatic

object NearestNeighborVisualizer {
	@JvmStatic
	fun main(args: Array<String>) {
		val filename = args[0]
		val `in` = In(filename)
		StdDraw.show(0)

		// initialize the two data structures with point from standard input
		val brute = PointST<Int>()
		val kdtree = KdTreeST<Int>()
		var i = 0
		while (!`in`.isEmpty) {
			val x = `in`.readDouble()
			val y = `in`.readDouble()
			val p = Point2D(x, y)
			kdtree.put(p, i)
			brute.put(p, i)
			i++
		}
		while (true) {

			// the location (x, y) of the mouse
			val x = StdDraw.mouseX()
			val y = StdDraw.mouseY()
			val query = Point2D(x, y)

			// draw all of the points
			StdDraw.clear()
			StdDraw.setPenColor(StdDraw.BLACK)
			StdDraw.setPenRadius(.01)
			for (p in brute.points()) p.draw()

//			// draw in red the nearest neighbor according to the brute-force algorithm
//			StdDraw.setPenRadius(.03)
//			StdDraw.setPenColor(StdDraw.RED)
//			brute.nearest(query).draw()
//			StdDraw.setPenRadius(.02)

			// draw in blue the nearest neighbor according to the kd-tree algorithm
			StdDraw.setPenColor(StdDraw.BLUE)
			kdtree.nearest(query)?.draw()
			StdDraw.show(0)
			StdDraw.show(40)
		}
	}
}