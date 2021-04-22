import edu.princeton.cs.algs4.Queue

class KdTreeST<Value> {
	inner class KdNode(
		var p: Point2D,
		var value: Value,
		var left: KdNode?,
		var right: KdNode?,
		var yAxis: Boolean,
	)

	private var root: KdNode? = null
	private var size = 0

	fun isEmpty(): Boolean {
		return size == 0
	}

	fun size(): Int {
		return size
	}

	private fun putHelper(node: KdNode?, p: Point2D, v: Value, yAxis: Boolean): KdNode {
		if (node == null) {
			size++
			return KdNode(p, v, null, null, yAxis)
		}

		if (node.p.x == p.x && node.p.y == p.y) {
			return node
		}

		if (
			(node.yAxis && node.p.y < p.y) ||
			(!node.yAxis && node.p.x < p.x)
		) {
			node.left = putHelper(node, p, v, !yAxis)
		} else {
			node.right = putHelper(node, p, v, !yAxis)
		}

		return node
	}

	fun put(p: Point2D, v: Value) {
		putHelper(root, p, v, true)
	}

	private fun getHelper(root: KdNode?, p: Point2D, vert: Boolean): Value? {
		if (root == null) return null
		val cmp = if (vert) root.p.y.compareTo(p.y) else root.p.x.compareTo(p.x)
		return when {
			cmp < 0 -> getHelper(root.left, p, !vert)
			cmp > 0 -> getHelper(root.right, p, !vert)
			root.p == p -> root.value
			else -> getHelper(root.right, p, !vert)
		}
	}

	fun get(p: Point2D): Value? {
		return getHelper(root, p, false)
	}

	fun contains(p: Point2D): Boolean {
		return getHelper(root, p, false) !== null
	}

	fun points(): Iterable<Point2D> {
		val returnQueue: Queue<Point2D> = Queue()
		if (root == null) return returnQueue
		val traversal = Queue<KdNode>()
		traversal.enqueue(root)
		while (!traversal.isEmpty) {
			val selectedNode = traversal.dequeue()
			returnQueue.enqueue(selectedNode.p)
			when {
				selectedNode.left != null -> traversal.enqueue(selectedNode.left)
				selectedNode.right != null -> traversal.enqueue(selectedNode.right)
			}
		}
		return returnQueue
	}

	fun range(r: RectHV): Iterable<Point2D> {
		val returnQueue = Queue<Point2D>()
		if (root == null) return returnQueue
		val traversal = Queue<KdNode>()
		traversal.enqueue(root)
		while (!traversal.isEmpty) {
			val selectedNode = traversal.dequeue()
			returnQueue.enqueue(selectedNode.p)
			if (r.contains(selectedNode.p)) {
				when {
					selectedNode.left != null -> traversal.enqueue(selectedNode.left)
					selectedNode.right != null -> traversal.enqueue(selectedNode.right)
				}
			}
		}
		return returnQueue
	}

	fun nearest(p: Point2D): Point2D? {
		var nearestPt: Point2D? = null
		var nearestDist = 0.0
		val traversal = Queue<KdNode>()
		traversal.enqueue(root)
		while (!traversal.isEmpty) {
			val selectedNode = traversal.dequeue()
			val dist = selectedNode.p.distanceSquaredTo(p)
			if (dist < nearestDist) {
				nearestPt = selectedNode.p
				nearestDist = dist
			}
			when {
				dist < 0 -> traversal.enqueue(selectedNode.left)
				dist > 0 -> traversal.enqueue(selectedNode.right)
				else -> break
			}
		}
		return nearestPt
	}
}