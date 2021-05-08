import java.util.*
import kotlin.math.abs

class Point2D(var x: Int, var y: Int) {
	operator fun plus(other: Point2D): Point2D {
		return Point2D(this.x + other.x, this.y + other.y)
	}
}

enum class MoveDirection(val transform: Point2D) {
	LEFT(Point2D(-1, 0)),
	RIGHT(Point2D(1, 0)),
	UP(Point2D(0, -1)),
	DOWN(Point2D(0, 1)),
}

data class Board(
	private var board: Array<IntArray>,
	private val N: Int = board.size,
) {
	fun get(p: Point2D) = board[p.y][p.x]
	fun set(p: Point2D, num: Int) {
		board[p.y][p.x] = num
	}

	override fun equals(other: Any?) = (other is Board)
					&& board.contentDeepEquals(other.board)

	private fun isOpen(p: Point2D): Boolean {
		return get(p) == 0
	}

	private fun inversions(): Int {
		val arr = IntArray(board.size * board.size - 1)

		var k = 0
		for (r in board.indices) {
			for (c in 0 until board[0].size) {
				if (get(Point2D(c, r)) != 0) {
					arr[k] = get(Point2D(c, r))
					++k
				}
			}
		}

		var count = 0
		for (i in 0 until arr.size - 1) {
			for (j in i + 1 until arr.size) {
				if (arr[i] > arr[j]) {
					count++
				}
			}
		}
		return count
	}

	private fun emptySlot(): Point2D {
		var row = -1
		var col = -1
		for (i in board.indices) {
			val idx = board[i].indexOf(0)
			if (idx != -1) {
				row = i
				col = idx
			}
		}
		return Point2D(col, row)
	}

	fun isSolvable(): Boolean {
		val invs = inversions()
		if (board.size % 2 == 0) {
			return (invs + emptySlot().x % 2 != 0)
		} else {
			return invs % 2 == 0
		}
	}

	private fun inBounds(pos: Point2D): Boolean {
		return when {
			pos.x < 0 || pos.y < 0 -> false
			pos.x > board.size - 1 || pos.y > board.size - 1 -> false
			else -> true
		}
	}

	fun neighbors(): Iterable<Board> {
		val stack = Stack<Board>()
		val slot = emptySlot()
		for (dir in MoveDirection.values()) {
			val target = slot + dir.transform
			if (!inBounds(target)) continue
			val cloned = board.map { row -> row.clone() }.toTypedArray()
			val newBoard = Board(cloned)
			newBoard.set(slot, newBoard.get(target))
			newBoard.set(target, 0)
			stack.add(newBoard)
		}
		return stack
	}

	fun manhattan(): Int {
		var res = 0
		for (i in board.indices) {
			for (j in board.indices) {
				val block = get(Point2D(j, i))
				if (block == 0) continue
				val expectedCol = ((block - 1) % N) + 1
				val expectedRow = ((block - 1) / N) + 1
				res += abs(expectedCol - (j + 1))
				res += abs(expectedRow - (i + 1))
			}
		}
		return res
	}

	fun isTarget(): Boolean {
		for (i in 0 until N) {
			for (j in 0 until N) {
				if (get(Point2D(j, i)) != ((i * N) + j + 1) % (N * N)) return false
			}
		}
		return true
	}

	override fun toString(): String {
		val out = StringBuilder()
		for (arr in board) {
			out.append(arr.contentToString())
			out.append("\n")
		}
		return out.toString()
	}

	override fun hashCode(): Int {
		var result = board.contentDeepHashCode()
		result = 31 * result + N
		return result
	}
}
