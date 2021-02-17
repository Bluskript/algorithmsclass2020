import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Assignment: Better3Sum_YI
 * 
 * Description: Write a program, Better3Sum_YI.java, so its order of growth is
 * N^2 log N.
 * 
 * 
 * Explanation: There are 2 for loops, which go from 0 to n. Inside, a binary
 * search is performed which has a time complexity of log n. As a result, the
 * time complexity becomes O(N^2 logN )
 * 
 * 
 * Language: Java 11
 * 
 * @author Danil Korennykh
 * @created December 16, 2020
 */
public class Better3Sum_DK {
	public static void main(String[] args) throws FileNotFoundException {
		var sc = new Scanner(new File("/home/amadeus/Desktop/School/algorithmsclass/ArrayStackOfIntegers/src/8Kints.txt"));
		var intList = new ArrayList<Integer>();

		var start = System.currentTimeMillis();
		while (sc.hasNextInt()) {
			intList.add(sc.nextInt());
		}

		Collections.sort(intList);

		var count = 0;
		var n = intList.size();
		for (var i = 0; i < n; i++) {
			for (var j = i + 1; j < n; j++) {
				int idx = Collections.binarySearch(intList, -(intList.get(i) + intList.get(j)));

				if (idx > j)
					count++;
			}
		}

		var elapsed = System.currentTimeMillis() - start;
		System.out.println("Elapsed: " + elapsed);
		System.out.println(count);
	}
}
