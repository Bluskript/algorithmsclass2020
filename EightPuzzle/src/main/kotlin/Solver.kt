import edu.princeton.cs.algs4.In
import edu.princeton.cs.algs4.MinPQ
import edu.princeton.cs.algs4.StdOut
import java.util.*

class Node(val board: Board, val moves: Int, val prev: Node?) : Comparable<Node>, Iterator<Board> {
	private fun manhattanPriority(): Int {
		return board.manhattan() + moves
	}

	override fun compareTo(other: Node) = manhattanPriority().compareTo(
		other.manhattanPriority(),
	)

	override fun hasNext(): Boolean {
		return prev != null
	}

	override fun next(): Board {
		return prev!!.board
	}
}

class Solver(initial: Board) {
	private var queue = MinPQ<Node>()
	private var solution: Node? = null

	init {
		queue.insert(
			Node(initial, 0, null)
		)
		var searchNode = queue.delMin()
		var prevNode = searchNode
		while (!searchNode.board.isTarget()) {
			for (neighbor in searchNode.board.neighbors()) {
				if (prevNode.board == neighbor) continue
				queue.insert(
					Node(
						neighbor,
						searchNode.moves + 1,
						searchNode
					)
				)
			}
			prevNode = searchNode
			searchNode = queue.delMin()
		}
		solution = searchNode
	}

	fun solution(): Iterable<Board> {
		solution?.let {
			var lastNode = it
			val output = LinkedList<Board>()
			do {
				output.add(0, lastNode.board)
				lastNode = lastNode.prev!!
			} while (lastNode.prev != null)
			return output
		}
		return emptyList()
	}

	fun moves(): Int {
		return solution?.moves ?: 0
	}
}

fun main(args: Array<String>) {
	val `in` = In(args[0])
	val N: Int = `in`.readInt()
	val blocks = Array(N) { IntArray(N) }
	for (i in 0 until N) for (j in 0 until N) blocks[i][j] = `in`.readInt()
	val initial = Board(blocks)
	if (initial.isSolvable()) {
		val solver = Solver(initial)
		StdOut.println("Minimum number of moves = " + solver.moves())
		for (board in solver.solution()) StdOut.println(board)
	} else {
		StdOut.println("Unsolvable puzzle")
	}
}
