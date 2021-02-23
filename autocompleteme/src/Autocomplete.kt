import java.io.FileReader
import java.util.*

class Autocomplete(private var terms: Array<Term>) {
	init {
		terms = terms.sortedArray()
	}

	fun allMatches(prefix: String): Array<Term> {
		val searchTerm = Term(prefix, 0.0)
		val cmp = Term.byPrefixOrder(prefix.length)
		val weightCmp = Term.byReverseWeightOrder()
		val start = firstIndexOf(terms, searchTerm, cmp)
		val end = lastIndexOf(terms, searchTerm, cmp)
		return arrayOf(*terms.sliceArray(start..end).sortedArrayWith(weightCmp))
	}

	fun numberOfMatches(prefix: String): Int {
		return allMatches(prefix).size
	}
}

fun main(args: Array<String>) {
	val filename = args[0]
	val sc = Scanner(FileReader(filename))
	val n = sc.nextInt()
	val terms = Array(n) {
		val weight = sc.nextDouble()
		val query = sc.nextLine().substring(1)
		Term(query, weight)
	}
	val k = args[1].toInt()
	val autocomplete = Autocomplete(terms)
	while (true) {
		println("search for a city:")
		val prefix = readLine() ?: break
		val results = autocomplete.allMatches(prefix)
		println("======= RESULTS =======")
		println("%d results found".format(results.size))
		for (i in 0..minOf(k, results.size-1)) {
			println(results[i])
		}
		println("======= END RESULTS =======")
	}
}