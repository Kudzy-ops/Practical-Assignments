import java.util.Arrays;

public class BubbleSortDemo {

    static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {      // compareTo exists because of the bound
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;                              // already sorted: stop early
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = {5, 2, 9, 1};
        String[] words = {"zebra", "apple", "mango"};

        bubbleSort(numbers);
        bubbleSort(words);

        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(words));

        // int[] raw = {3, 1, 2};
        // bubbleSort(raw);        // COMPILE ERROR
    }
}
