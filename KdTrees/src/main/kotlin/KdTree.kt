class KdTree {
	private var size: Int = 0
	private var root: KdNode? = null

	class KdNode(
		var x: Double,
		var y: Double,
		var left: KdNode?,
		var right: KdNode?,
		var yAxis: Boolean,
	)

	private fun insert(node: KdNode?, p: Point2D, yAxis: Boolean): KdNode {
		if (node == null) {
			size++
			return KdNode(p.x, p.y, null, null, yAxis)
		}

		if (node.x == p.x && node.y == p.y) {
			return node
		}

		if (
			(node.yAxis && node.y < p.y) ||
			(!node.yAxis && node.x < p.x)
		) {
			node.left = insert(node, p, !yAxis)
		} else {
			node.right = insert(node, p, !yAxis)
		}

		return node
	}

	fun insert(p: Point2D) {
		this.root = insert(this.root, p, true)
	}
}