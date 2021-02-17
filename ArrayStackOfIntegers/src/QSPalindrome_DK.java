import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Assignment: QSPalindrome_YI
 * 
 * Description: Create a system using a stack and a queue to test whether a
 * given string is a palindrome (i.e., the characters read the same forward or
 * backward).
 * 
 * Language: Java 11
 * 
 * @author Danil Korennykh
 * @created December 15, 2020
 */
public class QSPalindrome_DK {
	public static void main(String[] args) {
		var palindStack = new Stack<Character>();
		var palindQueue = new LinkedList<Character>();
		var sc = new Scanner(System.in);

		var input = sc.nextLine();

		for (char c : input.toCharArray()) {
			palindStack.push(c);
			palindQueue.add(c);
		}

		boolean palindrome = true;

		while (!palindQueue.isEmpty()) {
			if (!palindQueue.pop().equals(palindStack.pop())) {
				palindrome = false;
				break;
			}
		}

		System.out.println(palindrome);
	}
}
