import edu.princeton.cs.algs4.In
import edu.princeton.cs.algs4.StdDraw
import kotlin.jvm.JvmStatic

object RangeSearchVisualizer {
	@JvmStatic
	fun main(args: Array<String>) {
		val filename = args[0]
		val `in` = In(filename)
		StdDraw.show(0)

		// initialize the data structures with N points from standard input
		val brute = PointST<Int>()
		val kdtree: KdTreeST<Int> = KdTreeST()
		var i = 0
		while (!`in`.isEmpty) {
			val x = `in`.readDouble()
			val y = `in`.readDouble()
			val p = Point2D(x, y)
			kdtree.put(p, i)
			brute.put(p, i)
			i++
		}
		var x0 = 0.0
		var y0 = 0.0 // initial endpoint of rectangle
		var x1 = 0.0
		var y1 = 0.0 // current location of mouse
		var isDragging = false // is the user dragging a rectangle

		// draw the points
		StdDraw.clear()
		StdDraw.setPenColor(StdDraw.BLACK)
		StdDraw.setPenRadius(.01)
		for (p in brute.points()) p.draw()
		while (true) {
			StdDraw.show(40)

			// user starts to drag a rectangle
			if (StdDraw.isMousePressed() && !isDragging) {
				x0 = StdDraw.mouseX()
				y0 = StdDraw.mouseY()
				isDragging = true
				continue
			} else if (StdDraw.isMousePressed() && isDragging) {
				x1 = StdDraw.mouseX()
				y1 = StdDraw.mouseY()
				continue
			} else if (!StdDraw.isMousePressed() && isDragging) {
				isDragging = false
			}
			val rect = RectHV(
				Math.min(x0, x1), Math.min(y0, y1),
				Math.max(x0, x1), Math.max(y0, y1)
			)
			// draw the points
			StdDraw.clear()
			StdDraw.setPenColor(StdDraw.BLACK)
			StdDraw.setPenRadius(.01)
			for (p in brute.points()) p.draw()

			// draw the rectangle
			StdDraw.setPenColor(StdDraw.BLACK)
			StdDraw.setPenRadius()
			rect.draw()

			// draw the range search results for brute-force data structure in red
			StdDraw.setPenRadius(.03)
			StdDraw.setPenColor(StdDraw.RED)
			for (p in brute.range(rect)) p.draw()

			// draw the range search results for kd-tree in blue
			StdDraw.setPenRadius(.02)
			StdDraw.setPenColor(StdDraw.BLUE)
			for (p in kdtree.range(rect)) p.draw()
			StdDraw.show(40)
		}
	}
}