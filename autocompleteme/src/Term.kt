import java.lang.IllegalArgumentException
import java.util.Comparator

class Term(private val query: String, private val weight: Double) : Comparable<Term> {
    init {
        if (weight < 0) {
            throw IllegalArgumentException("weight must be positive")
        }
    }

    companion object {
        fun byPrefixOrder(r: Int): Comparator<Term> {
            return Comparator.comparing { t: Term -> t.query.substring(0, minOf(t.query.length, r)) }
        }

        fun byReverseWeightOrder(): Comparator<Term> {
            return Comparator.comparingDouble { t: Term -> t.weight }.reversed()
        }
    }

    override fun toString(): String {
        return weight.toString() + "\t" + query
    }

    override fun compareTo(other: Term): Int {
        return query.compareTo(other.query)
    }
}