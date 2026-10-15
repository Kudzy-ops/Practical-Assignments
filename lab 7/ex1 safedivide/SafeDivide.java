import java.util.InputMismatchException;
import java.util.Scanner;

public class SafeDivide {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Enter first integer: ");
            int a = in.nextInt();           // InputMismatchException if the text is not an int
            System.out.print("Enter second integer: ");
            int b = in.nextInt();
            System.out.println(a + " / " + b + " = " + (a / b));   // ArithmeticException if b == 0
        } catch (InputMismatchException e) {
            System.out.println("Please enter whole numbers only.");
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero.");
        } finally {
            in.close();                     // always release the resource
        }
    }
}
