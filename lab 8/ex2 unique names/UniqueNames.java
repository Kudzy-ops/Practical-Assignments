import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueNames {
    public static void main(String[] args) {
        List<String> names = List.of("Tariro", "Farai", "Tariro", "Rudo", "Farai", "Kuda");

        Set<String> unique = new LinkedHashSet<>(names);     // a Set rejects duplicates
        List<String> result = new ArrayList<>(unique);       // back to a List if one is needed

        System.out.println("Original: " + names);
        System.out.println("Unique:   " + result);
    }
}
