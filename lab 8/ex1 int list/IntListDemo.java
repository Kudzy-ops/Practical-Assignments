import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntListDemo {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(4, 8, 15, 16, 23, 42, 7, 1, 30, 12));

        int sum = 0;
        for (int n : numbers) {          // auto-unboxing: Integer -> int
            sum += n;
        }

        int max = Collections.max(numbers);

        List<Integer> reversed = new ArrayList<>(numbers);   // copy, so the original keeps its order
        Collections.reverse(reversed);

        System.out.println("Sum: " + sum);
        System.out.println("Maximum: " + max);
        System.out.println("Reversed: " + reversed);
    }
}
