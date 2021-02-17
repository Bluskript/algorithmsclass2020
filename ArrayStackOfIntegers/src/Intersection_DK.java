import java.util.Scanner;

/**
 * Assignment: Intersection_YI
 * 
 * Description: Create a system to simulate vehicles at an intersection. Assume
 * that there is one lane going in each of four directions, with stoplights
 * facing each direction. Vary the arrival average of vehicles in each direction
 * and the frequency of the light changes to view the “behavior” of the
 * intersection.
 * 
 * Language: Java 11
 * 
 * @author Danil Korennykh
 * @created December 15, 2020
 */
public class Intersection_DK {
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		var left = new ArrayStackOfIntegers_DK(16);
		var right = new ArrayStackOfIntegers_DK(16);
		var bottom = new ArrayStackOfIntegers_DK(16);
		var top = new ArrayStackOfIntegers_DK(16);
		var leftRight = true;

		int id = 0;

		while (true) {
			sc.nextLine();
			var rng = Math.random();

			if (rng < 0.25) {
				left.push(id);
			} else if (rng < 0.5) {
				right.push(id);
			} else if (rng < 0.75) {
				top.push(id);
			} else if (rng < 1) {
				bottom.push(id);
			}
			id++;

			if (leftRight) {
				if (!left.isEmpty())
					left.pop();
				if (!right.isEmpty())
					right.pop();
			} else {
				if (!top.isEmpty())
					top.pop();
				if (!bottom.isEmpty())
					bottom.pop();
			}

			var shouldChangeDirection = Math.random();
			if (shouldChangeDirection < 0.2) {
				leftRight = !leftRight;
			}

			System.out.printf("Traffic Light: %s\r\nLeft %d\r\nRight %d\r\nTop %d\r\nBottom %d\r\n",
					leftRight ? "left and right" : "top and bottom", left.getSize(), right.getSize(), top.getSize(),
					bottom.getSize());
		}
	}
}
