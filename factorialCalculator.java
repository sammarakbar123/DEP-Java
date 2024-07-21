package Task2;
import java.util.Scanner;

public class factorialCalculator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = s.nextInt();

        long factorial = calculateFactorial(number);

        System.out.println("Factorial of " + number + " is: " + factorial);
    }

    public static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
