import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "The cat and the dog and the bird";

        // 1) count: word -> number of occurrences
        Map<String, Integer> freq = new HashMap<>();
        for (String word : paragraph.toLowerCase().split(" ")) {
            freq.merge(word, 1, Integer::sum);       // add 1, or start at 1
        }

        // 2) a HashMap has no order, so copy the entries to a list and sort that
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> {
            int byCount = b.getValue().compareTo(a.getValue());   // higher count first
            if (byCount != 0) return byCount;
            return a.getKey().compareTo(b.getKey());              // tie: alphabetical
        });

        for (Map.Entry<String, Integer> e : entries) {
            System.out.println(e.getKey() + "=" + e.getValue());
        }
    }
}
