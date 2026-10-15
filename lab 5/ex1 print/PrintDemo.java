import java.util.Arrays;

public class PrintDemo {

    static void print(int x)      { System.out.println("int: " + x); }
    static void print(double x)   { System.out.println("double: " + x); }
    static void print(String x)   { System.out.println("String: " + x); }
    static void print(int[] x)    { System.out.println("int[]: " + Arrays.toString(x)); }

    public static void main(String[] args) {
        print(5);
        print(2.5);
        print("hi");
        print(new int[] {1, 2, 3});

        print('A');     // no print(char), so char is widened to int
        print(7L);      // no print(long), so long is widened to double
    }
}
