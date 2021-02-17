import java.util.Scanner;

/**
 * Assignment: ArrayStackOfIntegers ADT - Calculator
 * 
 * Description: Use your ArrayStackOfIntegers ADT for the following assignment:
 * Design and implement a client to calculate the following postfix expression:
 * 8 4 -3 * 1 5 + / *
 * 
 * Language: Java 11
 * 
 * @author Danil Korennykh
 * @created December 9, 2020
 */
public class PostfixCalculator_DK {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        ArrayStackOfIntegers_DK stackOfIntegers = new ArrayStackOfIntegers_DK(32);
        System.out.println("Enter a postfix expression");
        var expression = sc.nextLine();
        var split = expression.split(" ");
        for (var s : split) {
            try {
                stackOfIntegers.push(Integer.parseInt(s));
            } catch (Exception e) {
                int op2 = stackOfIntegers.pop();
                int op1 = stackOfIntegers.pop();
                switch (s) {
                    case "+": {
                        stackOfIntegers.push(op1 + op2);
                        break;
                    }
                    case "-": {
                        stackOfIntegers.push(op1 - op2);
                        break;
                    }
                    case "/": {
                        stackOfIntegers.push(op1 / op2);
                        break;
                    }
                    case "*": {
                        stackOfIntegers.push(op1 * op2);
                        break;
                    }
                }
            }
        }
        var result = stackOfIntegers.pop();

        System.out.println(result);

        sc.close();
    }
}
/**
 * Input
 * 
 * 8 4 -3 * 1 5 + / *
 * 
 * Output
 * 
 * -16
 */